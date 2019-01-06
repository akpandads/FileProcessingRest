package com.pearson.uploadresumejar.endpoints.outbound;

import com.google.gson.Gson;
import com.pearson.uploadresumejar.domain.applications.ApplicationDomain;
import com.pearson.uploadresumejar.domain.applications.FailedPutRequests;
import com.pearson.uploadresumejar.domain.applications.putresumedomain.ResumeDetails;
import com.pearson.uploadresumejar.domain.applications.putresumedomain.ResumeUploadPutDomain;
import com.pearson.uploadresumejar.domain.session.GetInterviewRequestProperites;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateResumeEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(UpdateResumeEndpoint.class);

    public List<FailedPutRequests> updateResumes(List<ApplicationDomain> applicationDomainList, String uploadResumeUrl, GetInterviewRequestProperites getInterviewRequestProperites){
        List<FailedPutRequests> failedPutRequestsList = new ArrayList<>();
        for(ApplicationDomain applicationDomain:applicationDomainList){
            for (int i =0;i<applicationDomain.getPositionId().size();i++){
                uploadResumeUrl = uploadResumeUrl.replace("POSITIONID",applicationDomain.getPositionId().get(i));
                uploadResumeUrl = uploadResumeUrl.replace("INTERVIEWID",applicationDomain.getInterviewId().get(i));

                ResumeDetails resumeDetails = new ResumeDetails();
                resumeDetails.setFilename(applicationDomain.getFileName());
                resumeDetails.setContent(applicationDomain.getFileContent());

                ResumeUploadPutDomain resumeUploadPutDomain = new ResumeUploadPutDomain();
                resumeUploadPutDomain.setResumeDetails(resumeDetails);

                Gson gson = new Gson();
                String jsonResume = gson.toJson(resumeUploadPutDomain);

                boolean reqSuccess = putResumeRequest(uploadResumeUrl,getInterviewRequestProperites,jsonResume);
                if(!reqSuccess){
                    FailedPutRequests failedPutRequests = new FailedPutRequests();
                    failedPutRequests.setUrl(uploadResumeUrl);
                    failedPutRequests.setPostionId(applicationDomain.getPositionId().get(i));
                    failedPutRequests.setInterviewId(applicationDomain.getInterviewId().get(i));
                    failedPutRequests.setRequest(jsonResume);
                    failedPutRequestsList.add(failedPutRequests);
                }
                uploadResumeUrl = uploadResumeUrl.replace(applicationDomain.getPositionId().get(i),"POSITIONID");
                uploadResumeUrl = uploadResumeUrl.replace(applicationDomain.getInterviewId().get(i),"INTERVIEWID");
            }

        }
        return failedPutRequestsList;
    }

    public boolean putResumeRequest(String uploadResumeUrl, GetInterviewRequestProperites getInterviewRequestProperites, String jsonResumex){
        boolean reqSuccess = false;
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(uploadResumeUrl);
        put.addHeader("X-CSRFToken", getInterviewRequestProperites.getXCSRFToken());
        put.addHeader("Cookie", getInterviewRequestProperites.getCookie());
        put.addHeader("Content-Type", "application/json");
        try{
            StringEntity entity = new StringEntity(jsonResumex);
            put.setEntity(entity);
            logger.info("Hitting Position details Url------------->"+uploadResumeUrl);
            HttpResponse response = client.execute(put);
            if(response.getStatusLine().getStatusCode()>=200 && response.getStatusLine().getStatusCode()<300){
                logger.info("Put Succesful sent for "+jsonResumex);
                reqSuccess= true;
            }
            else{
                logger.info("Status code "+response.getStatusLine().getStatusCode());
            }
        }
        catch (Exception e){
            logger.error("Error while parsing String to Json ",e);
        }
        return reqSuccess;
    }
}
