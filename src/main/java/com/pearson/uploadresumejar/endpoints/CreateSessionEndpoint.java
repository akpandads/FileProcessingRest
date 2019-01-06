package com.pearson.uploadresumejar.endpoints;

import com.pearson.uploadresumejar.domain.session.GetInterviewRequestProperites;

import com.pearson.uploadresumejar.domain.session.SessionProperties;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CreateSessionEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(CreateSessionEndpoint.class);

    @Autowired
    SessionProperties sessionProperties;

    public GetInterviewRequestProperites getSession(String sessionUrl){
        GetInterviewRequestProperites getInterviewRequestProperites = new GetInterviewRequestProperites();
        HttpClient client = new DefaultHttpClient();
        logger.info("Hitting Create Seession / Login Url------------->"+sessionUrl);
        HttpPost post = new HttpPost(sessionUrl);
        post.addHeader("content-type", "application/json"); //x-www-form-urlencoded");
        try {

            StringEntity input =  new StringEntity("{\"impersonate\":\"livingston.chinnadurai@prudential.com\"," +
                    "\"apiKey\":\"Ej8K2uCmVWFBRxXXEPDRUp:4f5572e316beec701e91e8ac7f25d809e67f33a80f87a1cb3edca48624069369\"," +
                    "\"applicationToken\":\"test_public_token\"," +
                    "\"version\":\"1.2.0\"} ");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            logger.info("Get Session response status "+response.getStatusLine());
            if (response.getStatusLine().getStatusCode() == 200) {
                for (Header header : response.getAllHeaders()) {
                    if (header.getName().equalsIgnoreCase("csrftoken")) {
                        getInterviewRequestProperites.setXCSRFToken(header.getValue());
                    }
                    if (header.getName().equalsIgnoreCase("Set-Cookie")) {
                        getInterviewRequestProperites.setCookie(header.getValue());
                    }
                    if (header.getName().equalsIgnoreCase("X-HvApi-Session-Key")) {
                        getInterviewRequestProperites.setSessionKey(header.getValue());
                    }
                }
            }
            else{
                logger.info("Error while getting session. Respponse Code :"+response.getStatusLine());
                logger.info("Exiting System. Try again");
                System.exit(0);
            }
        }
        catch (Exception e){
            logger.error("Exception while creating Session",e);
            logger.info("Exiting System. Try again");
            System.exit(0);
        }
        return getInterviewRequestProperites;

    }
}
