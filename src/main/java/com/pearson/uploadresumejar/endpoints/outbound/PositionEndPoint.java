package com.pearson.uploadresumejar.endpoints.outbound;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pearson.uploadresumejar.domain.applications.ApplicationDomain;
import com.pearson.uploadresumejar.domain.postions.PositionResponse;
import com.pearson.uploadresumejar.domain.session.GetInterviewRequestProperites;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.apache.http.client.HttpClient;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class PositionEndPoint {

    private static final Logger logger = LoggerFactory.getLogger(PositionEndPoint.class);

    public void populatePositionIdForEachCandidate(List<ApplicationDomain> applicationDomainList, String positionUrl,GetInterviewRequestProperites getInterviewRequestProperites){
        for(ApplicationDomain applicationDomain: applicationDomainList){
            positionUrl = positionUrl.replace("REQUISITIONNUMBER",applicationDomain.getRequisitionNumber());
            JsonArray positionResponse = getPositionDetails(getInterviewRequestProperites,positionUrl);
            List<String> positionIdList= extractPositionIds(positionResponse);
            for(String positionId: positionIdList){
                applicationDomain.getPositionId().add(positionId);
            }
            positionUrl = positionUrl.replace(applicationDomain.getRequisitionNumber(),"REQUISITIONNUMBER");
        }
    }

    public JsonArray getPositionDetails(GetInterviewRequestProperites getInterviewRequestProperites, String positionUrl){

        //String url = "https://stgprudential.stghv.com/api/v1/positions/?qf=[externalId:tee:common req]&include=integrationPositions";
        JsonArray jsonObj=null;
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet(positionUrl);

        get.addHeader("X-CSRFToken", getInterviewRequestProperites.getXCSRFToken());
        get.addHeader("Cookie", getInterviewRequestProperites.getCookie());
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
    }

    /*private static JsonArray getPositionDetails(String authHeadet, String positionUrl){

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

    public static List<String> extractPositionIds(JsonArray jsonObject){
        List<String> positionIds= new ArrayList<>();
        logger.info("Converted to json object start");
        Type listType = new TypeToken<ArrayList<PositionResponse>>(){}.getType();
        Gson gson = new Gson();
        List<PositionResponse> positionResponseDomainsList = gson.fromJson(jsonObject,listType);
        logger.info("Converted to json array with size"+positionResponseDomainsList.size());
        logger.info("Extracting just the postion Id");
        for(PositionResponse postion : positionResponseDomainsList){
            positionIds.add(postion.getId());
        }
        logger.info("List of postion Id returned "+positionIds );
        return positionIds;
    }
}
