package com.cp.jiaguo.producer;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ResultHandler implements FutureCallback<HttpResponse> {
    private static final Logger log = LoggerFactory.getLogger(ResultHandler.class);

    @Override
    public void completed(HttpResponse httpResponse) {
        log.info("Request completed.");
        String result = null;
        try {
            result = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
            log.info(result);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
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
}
