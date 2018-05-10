package com.cp.jiaguo.producer;

import org.apache.http.HttpResponse;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ResultHandler implements FutureCallback<HttpResponse> {
    private static final Logger log = LoggerFactory.getLogger(ResultHandler.class);
    private ResultParser resultParser = new ResultParser();

    @Override
    public void completed(HttpResponse httpResponse) {
        log.info("Request completed.");
        String responseString = null;
        try {
            responseString = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        } catch (IOException ex) {
            log.error("Error when get response string.", ex);
        }

        ResultModel resultModel = resultParser.parse(responseString);
        if (resultModel != null) {
            save(resultModel);
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

    private void save(ResultModel resultModel){

    }
}
