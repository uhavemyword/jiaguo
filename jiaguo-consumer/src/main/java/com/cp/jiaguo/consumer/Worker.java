package com.cp.jiaguo.consumer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Worker {
    private static final Logger log = LoggerFactory.getLogger(Worker.class);
    private static final String QUEUE_NAME = "jiaguo";
    private ConnectionFactory rabbitFactory;
    private Connection connection;
    private Channel channel;
    @Autowired
    private ResultHandler resultHandler;
    @Autowired
    private Config config;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        rabbitFactory = new ConnectionFactory();
        rabbitFactory.setHost(config.getRabbitHost());
        rabbitFactory.setPort(config.getRabbitPort());
        rabbitFactory.setUsername(config.getRabbitUser());
        rabbitFactory.setPassword(config.getRabbitPassword());
        connection = rabbitFactory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    }

    @Scheduled(fixedRate = 3000)
    public void doWork() throws IOException {
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    resultHandler.save(message);
                } catch (Exception ex) {
                    log.error("Error when save the message: " + message, ex);
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
