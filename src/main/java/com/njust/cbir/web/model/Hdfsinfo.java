package com.njust.cbir.web.model;

public class Hdfsinfo {
    private Integer id;

    private String url;

    private Long blocksize;

    private Integer blocknumber;

    private String time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getBlocksize() {
        return blocksize;
    }

    public void setBlocksize(Long blocksize) {
        this.blocksize = blocksize;
    }

    public Integer getBlocknumber() {
        return blocknumber;
    }

    public void setBlocknumber(Integer blocknumber) {
        this.blocknumber = blocknumber;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }
}