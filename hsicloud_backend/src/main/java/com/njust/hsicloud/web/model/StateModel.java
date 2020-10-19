package com.njust.hsicloud.web.model;

public class StateModel {
    private int cid;
    private String value;
    public Integer getCid() {
        return cid;
    }
    public void setCid(int cid) {
        this.cid = cid ;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }
}
