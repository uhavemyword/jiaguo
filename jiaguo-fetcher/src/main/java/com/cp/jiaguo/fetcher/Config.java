package com.cp.jiaguo.fetcher;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my.config")
public class Config {
    private int lastId;
    private String userAgent;
    private String lastIdFile;

    public String getLastIdFile() {
        return lastIdFile;
    }

    public void setLastIdFile(String lastIdFile) {
        this.lastIdFile = lastIdFile;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }
}
