package com.pearson.uploadresumejar.endpoints.outbound;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pearson.uploadresumejar.domain.applications.ApplicationDomain;
import com.pearson.uploadresumejar.domain.interview.InterviewResponseDomain;
import com.pearson.uploadresumejar.domain.session.GetInterviewRequestProperites;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class InterviewEndPoint {
    private static final Logger logger = LoggerFactory.getLogger(InterviewEndPoint.class);

    public void populateInterviewIdsForCandidate(List<ApplicationDomain> applicationDomainList, String interviewUrl, GetInterviewRequestProperites getInterviewRequestProperites){
        for(ApplicationDomain applicationDomain: applicationDomainList){
            List<String> interviewIds = new ArrayList<>();
            for(String positionId : applicationDomain.getPositionId()){
                interviewUrl=interviewUrl.replace("POSITIONID",positionId);
                interviewUrl=interviewUrl.replace("EMAILID",applicationDomain.getTaleoCandidateEmail());
                JsonArray interview = getInterviewIdDetails(getInterviewRequestProperites,interviewUrl);
                interviewIds.add(extractInterviewIds((interview)));
                interviewUrl=interviewUrl.replace(positionId,"POSITIONID");
                interviewUrl=interviewUrl.replace(applicationDomain.getTaleoCandidateEmail(),"EMAILID");
            }
            for(String interviewId :interviewIds){
                applicationDomain.getInterviewId().add(interviewId);
            }
        }
    }

    public JsonArray getInterviewIdDetails(GetInterviewRequestProperites getInterviewRequestProperites, String interviewUrl){

        //String url = "https://stgprudential.stghv.com/api/v1/positions/?qf=[externalId:tee:common req]&include=integrationPositions";
        JsonArray jsonObj=null;
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(interviewUrl);

        get.addHeader("X-CSRFToken", getInterviewRequestProperites.getXCSRFToken());
        get.addHeader("Cookie", getInterviewRequestProperites.getCookie());
        get.addHeader("Content-Type", "application/json");
        try {
            logger.info("Hitting Position details Url------------->"+interviewUrl);
            HttpResponse response = client.execute(get);
            JsonParser jsonParser = new JsonParser();
            jsonObj=(JsonArray)jsonParser.parse(new BasicResponseHandler().handleResponse(response));
        }
        catch (Exception e){
            logger.info("Exception while getting positions call",e);
            System.exit(0);
        }
        return jsonObj;
    }

    /*private static JsonArray getInterviewIdDetails(String authHeadet, String positionUrl){

        //String url = "https://stgprudential.stghv.com/api/v1/positions/?qf=[externalId:tee:common req]&include=integrationPositions";
        JsonArray jsonObj=null;
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(positionUrl);

        get.addHeader("Authorization", authHeadet);
        get.addHeader("Content-Type", "application/json");
        try {
            logger.info("Hitting Position details Url------------->"+positionUrl);
            HttpResponse response = client.execute(get);
            JsonParser jsonParser = new JsonParser();
            jsonObj=(JsonArray)jsonParser.parse(new BasicResponseHandler().handleResponse(response));
        }
        catch (Exception e){
            logger.info("Exception while getting positions call",e);
            System.exit(0);
        }
        return jsonObj;
    }*/

    public static String extractInterviewIds(JsonArray jsonObject){
        logger.info("Converted to json object start");
        Type listType = new TypeToken<ArrayList<InterviewResponseDomain>>(){}.getType();
        Gson gson = new Gson();
        List<InterviewResponseDomain> interviewResponseDomainsList = gson.fromJson(jsonObject,listType);
        logger.info("Converted to json array with size"+interviewResponseDomainsList.size());
        logger.info("Extracted just the interview Id of Interviews : "+interviewResponseDomainsList.get(0).getId());
        return interviewResponseDomainsList.get(0).getId();
    }
}

