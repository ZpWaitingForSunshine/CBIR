package com.njust.hsicloud.web.model;

public class Excutive {
    private Integer id;

    private String uerName;

    private Integer nodesNumbers;

    private String nodesName;

    private String paramters;

    private String dagLink;

    private String dispatchResult;

    private String dispatchLocalLink;

    private String gantLink;

    private String result;

    private String submissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUerName() {
        return uerName;
    }

    public void setUerName(String uerName) {
        this.uerName = uerName == null ? null : uerName.trim();
    }

    public Integer getNodesNumbers() {
        return nodesNumbers;
    }

    public void setNodesNumbers(Integer nodesNumbers) {
        this.nodesNumbers = nodesNumbers;
    }

    public String getNodesName() {
        return nodesName;
    }

    public void setNodesName(String nodesName) {
        this.nodesName = nodesName == null ? null : nodesName.trim();
    }

    public String getParamters() {
        return paramters;
    }

    public void setParamters(String paramters) {
        this.paramters = paramters == null ? null : paramters.trim();
    }

    public String getDagLink() {
        return dagLink;
    }

    public void setDagLink(String dagLink) {
        this.dagLink = dagLink == null ? null : dagLink.trim();
    }

    public String getDispatchResult() {
        return dispatchResult;
    }

    public void setDispatchResult(String dispatchResult) {
        this.dispatchResult = dispatchResult == null ? null : dispatchResult.trim();
    }

    public String getDispatchLocalLink() {
        return dispatchLocalLink;
    }

    public void setDispatchLocalLink(String dispatchLocalLink) {
        this.dispatchLocalLink = dispatchLocalLink == null ? null : dispatchLocalLink.trim();
    }

    public String getGantLink() {
        return gantLink;
    }

    public void setGantLink(String gantLink) {
        this.gantLink = gantLink == null ? null : gantLink.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId == null ? null : submissionId.trim();
    }
}