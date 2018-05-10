package com.cp.jiaguo.fetcher;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class Worker {

    private static final Logger log = LoggerFactory.getLogger(Worker.class);
    private final CloseableHttpAsyncClient httpClient;
    private final ResultHandler resultHandler = new ResultHandler();
    @Autowired
    private Config config;

    public Worker() {
        Application.loadLastId();
        httpClient = HttpAsyncClients.custom()
                .setDefaultCookieStore(new BasicCookieStore())
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")
                .build();
        httpClient.start();
    }

    @Scheduled(fixedRate = 5000)
    public void doWork() {
        URI uri = getNextUri();
        visit(uri);
    }

    private URI getNextUri() {
        config.setLastId(config.getLastId() + 1);
        return URI.create(String.format("https://www.zhenguo.com/housing/%d/", config.getLastId()));
    }

    public void visit(URI uri) {
        log.info("visiting {}", uri);
        final HttpGet request = new HttpGet(uri);
        httpClient.execute(request, resultHandler);
    }
}
