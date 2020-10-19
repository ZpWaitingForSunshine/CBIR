package com.njust.hsicloud.web.model;

public class Specdata {
    private Integer id;

    private String specname;

    private Integer speclibid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname == null ? null : specname.trim();
    }

    public Integer getSpeclibid() {
        return speclibid;
    }

    public void setSpeclibid(Integer speclibid) {
        this.speclibid = speclibid;
    }
}