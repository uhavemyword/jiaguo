package com.cp.jiaguo.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class Worker {
    private static final Logger log = LoggerFactory.getLogger(Worker.class);
    private static final String QUEUE_NAME = "jiaguo";
    private ConnectionFactory rabbitFactory;
    private Connection connection;

    public Worker(Config config) throws IOException, TimeoutException {
        rabbitFactory = new ConnectionFactory();
        rabbitFactory.setHost(config.getRabbitHost());
        rabbitFactory.setPort(config.getRabbitPort());
        rabbitFactory.setUsername(config.getRabbitUser());
        rabbitFactory.setPassword(config.getRabbitPassword());
        connection = rabbitFactory.newConnection();
    }

    @Scheduled(fixedRate = 3000)
    public void doWork() throws IOException, TimeoutException {
        consume();
    }

    private void consume() {
        try (Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicConsume(QUEUE_NAME, true, new Receiver(channel));
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
