package com.cp.jiaguo.webapi.model;

public class Product extends BaseEntity {
    private int externalId;
    private String infoJson;

    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    public String getInfoJson() {
        return infoJson;
    }

    public void setInfoJson(String infoJson) {
        this.infoJson = infoJson;
    }
}
