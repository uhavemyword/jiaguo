package com.cp.jiaguo.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

public class Receiver extends DefaultConsumer {
    private static final Logger log = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Receiver(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope,
                               AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        JSONObject jsonObject = null;
        Integer id;
        try {
            jsonObject = JSON.parseObject(message);
            id = jsonObject.getJSONObject("product").getInteger("id");
        } catch (Exception ex) {
            log.error("Received message is not in expected JSON format. Message: {}", message, ex);
            return;
        }

        jdbcTemplate.update("insert into product(product_id, product_json) values(?,?)", id, message);
        log.debug("Product with {} saved in database.", id);
    }
}
