package com.cp.jiaguo.producer;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URI;

@Component
public class Worker {

    private static final Logger log = LoggerFactory.getLogger(Worker.class);
    private CloseableHttpAsyncClient httpClient;
    @Autowired
    private ResultHandler resultHandler;
    @Autowired
    private Config config;

    @PostConstruct
    public void init() {
        ProducerApplication.loadLastId();
        httpClient = HttpAsyncClients.custom()
                .setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .build();
        httpClient.start();
    }

    @PreDestroy
    public void clean() throws IOException {
        if (httpClient != null) {
            httpClient.close();
        }
    }

    @Scheduled(fixedRateString = "${my.task.interval}")
    public void doWork() {
        URI uri = getNextUri();
        visit(uri);
    }

    private URI getNextUri() {
        config.setLastId(config.getLastId() + 1);
        return URI.create(String.format("https://www.zhenguo.com/housing/%d/", config.getLastId()));
    }

    public void visit(URI uri) {
        log.debug("visiting {}", uri);
        final HttpGet request = new HttpGet(uri);
        httpClient.execute(request, resultHandler);
    }
}
