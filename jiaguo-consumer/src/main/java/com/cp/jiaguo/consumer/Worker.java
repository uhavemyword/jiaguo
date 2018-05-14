package com.cp.jiaguo.consumer;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
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
    public void init() {
        rabbitFactory = new ConnectionFactory();
        rabbitFactory.setHost(config.getRabbitHost());
        rabbitFactory.setPort(config.getRabbitPort());
        rabbitFactory.setUsername(config.getRabbitUser());
        rabbitFactory.setPassword(config.getRabbitPassword());
    }

    private boolean ensureChannelOpen() {
        try {
            if (connection == null || !connection.isOpen()) {
                connection = rabbitFactory.newConnection();
            }
            if (channel == null || !channel.isOpen()) {
                channel = connection.createChannel();
            }
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            log.debug("ensureChannelOpen() success.");
            return true;
        } catch (Exception ex) {
            log.error("ensureChannelOpen() failed.", ex);
        }
        return false;
    }

    @Scheduled(fixedRateString = "${my.task.interval}")
    public void doWork() throws IOException, InterruptedException, TimeoutException {
        log.debug("Start doWork().");
        if (!ensureChannelOpen()) {
            return;
        }

        int messageCount = (int) channel.messageCount(QUEUE_NAME);
        log.debug("{} message found in the {} queue.", messageCount, QUEUE_NAME);
        if (messageCount < 1) {
            return;
        }

        CountDownLatch latch = new CountDownLatch(messageCount);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    try {
                        resultHandler.save(message);
                    } catch (Exception ex) {
                        log.error("Error when save the message: " + message, ex);
                    } finally {
                        channel.basicAck(envelope.getDeliveryTag(), false);
                    }
                } finally {
                    latch.countDown();
                }
            }
        };

        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        latch.await();
        // in real world, it's better to keep a long lived channel rather than closing it.
        channel.close();
    }
}
