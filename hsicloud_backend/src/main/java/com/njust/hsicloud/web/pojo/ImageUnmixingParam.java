package com.njust.hsicloud.web.pojo;

public class ImageUnmixingParam {

    private String master;

    private int drivermemory;

    private int executormemory;

    private int executorcores;

    private String jars;

    private String params;

    private int imageid;

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getDrivermemory() {
        return drivermemory;
    }

    public void setDrivermemory(int drivermemory) {
        this.drivermemory = drivermemory;
    }

    public int getExecutormemory() {
        return executormemory;
    }

    public void setExecutormemory(int executormemory) {
        this.executormemory = executormemory;
    }

    public int getExecutorcores() {
        return executorcores;
    }

    public void setExecutorcores(int executorcores) {
        this.executorcores = executorcores;
    }

    public String getJars() {
        return jars;
    }

    public void setJars(String jars) {
        this.jars = jars;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
