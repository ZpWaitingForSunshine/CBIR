package com.njust.hsicloud.web.model;

import java.util.ArrayList;

public class nodeModel {
    private String cid;
    private String name;
    private String startTime;
    private ArrayList<String> sucessor=new ArrayList<>();

    private ArrayList<String>  processor=new ArrayList<>();
    private String submissionid;
    private String parallel;
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }
    public String getParallel() {
        return parallel;
    }

    public void setParallel(String parallel) {
        this.parallel = parallel == null ? null : parallel.trim();
    }
    public ArrayList<String> getSucessor() {
        return sucessor;
    }

    public void setSucessor(String sucessor) {
            this.sucessor.add(sucessor);
    }
    public ArrayList<String> getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
            this.processor.add(processor);
    }
    public String getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(String submissionid) {
        this.submissionid = submissionid == null ? null : submissionid.trim();
    }

}
