package com.pearson.uploadresumejar.domain.session;


import com.pearson.uploadresumejar.endpoints.CreateSessionEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SessionProperties {

    private static final Logger logger = LoggerFactory.getLogger(CreateSessionEndpoint.class);

    @Value("${app.createsession.endpoint}")
    private String sessionEndpointUrl;

    @Value("${app.createsession.impersonate}")
    private String impersonate;

    @Value("${app.createsession.apikey}")
    private String apiKey;

    @Value("${app.createsession.applicationToken}")
    private String applicationToken;

    @Value("${app.createsession.version}")
    private String version;

    public String getSessionEndpointUrl() {
        return sessionEndpointUrl;
    }

    public void setSessionEndpointUrl(String sessionEndpointUrl) {
        this.sessionEndpointUrl = sessionEndpointUrl;

    }

    public String getImpersonate() {
        return impersonate;
    }

    public void setImpersonate(String impersonate) {
        this.impersonate = impersonate;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
