package com.pearson.uploadresumejar.domain.applications;

public class FailedPutRequests {
    private String postionId;
    private String interviewId;
    private String url;
    private String request;

    public String getPostionId() {
        return postionId;
    }

    public void setPostionId(String postionId) {
        this.postionId = postionId;
    }

    public String getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(String interviewId) {
        this.interviewId = interviewId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
