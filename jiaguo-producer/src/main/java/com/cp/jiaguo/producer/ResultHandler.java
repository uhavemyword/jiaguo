package com.cp.jiaguo.producer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class ResultHandler implements FutureCallback<HttpResponse> {
    private static final Logger log = LoggerFactory.getLogger(ResultHandler.class);
    private final static String QUEUE_NAME = "jiaguo";

    private ResultParser resultParser = new ResultParser();
    private ConnectionFactory rabbitFactory;
    private Connection connection;

    public ResultHandler(Config config) throws IOException, TimeoutException {
        rabbitFactory = new ConnectionFactory();
        rabbitFactory.setHost(config.getRabbitHost());
        rabbitFactory.setPort(config.getRabbitPort());
        rabbitFactory.setUsername(config.getRabbitUser());
        rabbitFactory.setPassword(config.getRabbitPassword());
        connection = rabbitFactory.newConnection();
    }

    @Override
    public void completed(HttpResponse httpResponse) {
        log.debug("Request completed.");
        String responseString = null;
        try {
            responseString = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (IOException ex) {
            log.error("Error when get response string.", ex);
        }

        ResultModel resultModel = resultParser.parse(responseString);
        if (resultModel != null) {
            save(resultModel);
        } else {
            log.warn("Response string not mapped to model.");
        }
    }

    @Override
    public void failed(Exception ex) {
        log.error("Request failed.", ex);
    }

    @Override
    public void cancelled() {
        log.warn("Request cancelled.");
    }

    private void save(ResultModel resultModel) {
        try (Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            byte[] message = JSON.toJSONBytes(resultModel);
            channel.basicPublish("", QUEUE_NAME, null, message);
            log.debug("Message sent to the rabbit server.");

        } catch (TimeoutException ex) {
            log.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
