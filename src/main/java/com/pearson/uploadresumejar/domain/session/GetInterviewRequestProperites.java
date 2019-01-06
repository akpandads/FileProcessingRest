package com.pearson.uploadresumejar.domain.session;

public class GetInterviewRequestProperites {

    private String XCSRFToken;
    private String Cookie;
    private String SessionKey;


    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String sessionKey) {
        SessionKey = sessionKey;
    }
    public String getXCSRFToken() {
        return XCSRFToken;
    }

    public void setXCSRFToken(String XCSRFToken) {
        this.XCSRFToken = XCSRFToken;
    }

    public String getCookie() {
        return Cookie;
    }

    public void setCookie(String cookie) {
        Cookie = cookie;
    }
}
