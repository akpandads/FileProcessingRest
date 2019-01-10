package com.pearson.uploadresumejar;

import com.pearson.uploadresumejar.domain.applications.ApplicationDomain;
import com.pearson.uploadresumejar.domain.applications.FailedPutRequests;
import com.pearson.uploadresumejar.domain.session.GetInterviewRequestProperites;
import com.pearson.uploadresumejar.endpoints.CreateSessionEndpoint;
import com.pearson.uploadresumejar.endpoints.outbound.InterviewEndPoint;
import com.pearson.uploadresumejar.endpoints.outbound.PositionEndPoint;
import com.pearson.uploadresumejar.endpoints.outbound.UpdateResumeEndpoint;
import com.pearson.uploadresumejar.utils.CsvFileReaderWriter;
import com.pearson.uploadresumejar.utils.DirScanner;
import com.pearson.uploadresumejar.utils.Exceptions.*;
import com.pearson.uploadresumejar.utils.FileConetntDecoder;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class UploadresumejarApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UploadresumejarApplication.class);

    @Autowired
    public PositionEndPoint positionEndPoint;

    @Autowired
    public UpdateResumeEndpoint updateResumeEndpoint;

    @Autowired
    public InterviewEndPoint interviewEndPoint;

    @Autowired
    CreateSessionEndpoint createSessionEndpoint;

    @Autowired
    DirScanner dirScanner;

    GetInterviewRequestProperites getInterviewRequestProperites;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UploadresumejarApplication.class);
        app.run(args);
    }

    public void run(String... args) throws Exception {
        logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Jar Execution Start ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        logger.info("Resume upload jar start");
        Map<String,String> properties= readPropertiesFile();
        String positionUrl = properties.get("positionUrl");
        String loginUrl=properties.get("loginUrl");
        String interviewUrl = properties.get("interviewUrl");
        String uploadResumeUrl = properties.get("uploadResumeUrl");
        String fileLocation = properties.get("csvFileLoc");
        String fileNamePrefix =properties.get("fileNamePrefix");
        getInterviewRequestProperites =createSessionEndpoint.getSession(loginUrl);
        logger.info("Getting Session End with "+getInterviewRequestProperites);
        List<ApplicationDomain> applicationDomainList;
        try{
            String fileNameToRead = dirScanner.getFIleNameToBeRead(fileLocation,fileNamePrefix);
            applicationDomainList = CsvFileReaderWriter.readCsvFile(fileNameToRead);
            positionEndPoint.populatePositionIdForEachCandidate(applicationDomainList,positionUrl,getInterviewRequestProperites);
            interviewEndPoint.populateInterviewIdsForCandidate(applicationDomainList,interviewUrl,getInterviewRequestProperites);
            for(ApplicationDomain applicationDomain: applicationDomainList){
                for (int i =0;i<applicationDomain.getPositionId().size();i++){
                    logger.info("Printing all the details of each email id");
                    logger.info(applicationDomain.getTaleoCandidateEmail()+" :: "+applicationDomain.getPositionId().get(i)+ " :: "+applicationDomain.getInterviewId().get(i));
                }
            }
            List<FailedPutRequests> failedPutRequestsList=updateResumeEndpoint.updateResumes(applicationDomainList,uploadResumeUrl,getInterviewRequestProperites);
            if (failedPutRequestsList.size() != 0) {
                logger.info("Error in one or more put requests to file. Writing to file the list if errored requests");
                CsvFileReaderWriter.writeFailedRequestsToCsvFile(failedPutRequestsList);
                logger.info("put requests which failed wrttien into file : Failed_Resume_Put.csv");
            }
            logger.info("******************************** Jar ran succesfully****************************************");
            logger.info("********************************     Exiting Jar    ****************************************");
        }
        catch (EmptyCSVFileException e) {
            logger.error("Exception occurred  CSV file is empty ",e);
            logger.info("Exiting Application. Please provide correct csv file and run the jar again");
        }
        catch (InvalidCSVFileException ie){
            logger.error("Exception occurred while reading CSV file. Problem most probably with csv file header or file not found ",ie);
            logger.info("Exiting Application. Please provide correct csv file and run the jar again");
            System.exit(1);
        }
        catch (CSVFileWriteException ce){
            logger.error("Error while writing to csv file");
            System.exit(1);
        }
        catch (NoCsvFileFoundException noCSVFound){
            logger.error("No CSV file found with given file name.Please keep only one csv file in the location",noCSVFound);
            logger.info("Exiting Application");
            System.exit(1);
        }
        catch (MoreThanOneCSVFileFoundException moreThanOne){
            logger.error("Expected only one CSV with the given file prefix. Found more than one. Please keep only one csv file in the location",moreThanOne);
            logger.info("Exiting Application");
            System.exit(1);
        }
        catch (Exception e){
            logger.error("Error occured while running job to upload resume ",e);
            logger.info("Exiting Application. Please provide correct csv file and run the jar again");
            System.exit(1);
        }
    }

    private Map<String,String> readPropertiesFile() {
        logger.info(" Properties Read from file--------start" );
        Properties prop = new Properties();
        InputStream input = null;
        Map<String,String> propertyValues = new HashMap<>();
        try {

            input = new FileInputStream("resumeUploadConfig.properties");
            prop.load(input);
            // get the property value and print it out
            String loginUrl=prop.getProperty("loginUrl");
            String positionUrl=prop.getProperty("positionUrl");
            String interviewUrl = prop.getProperty("interviewUrl");
            String authHeader=prop.getProperty("authHeader");
            String resumeUploadUrl=prop.getProperty("uploadResumeUrl");
            String fileLoc = prop.getProperty("csvFileLoc");
            String fileNamePrefix = prop.getProperty("fileNamePrefix");
            if( fileNamePrefix==null || fileNamePrefix=="" || positionUrl==null || positionUrl=="" || resumeUploadUrl==null || resumeUploadUrl=="" || fileLoc==null || fileLoc==""
                    || interviewUrl==null || interviewUrl=="" || authHeader==null || authHeader=="" || loginUrl=="" || loginUrl==null)
            {
                throw new Exception("One or more required property file entry missing in properties File");
            }
            logger.info("Found All the properties. Loading inro Map");
            logger.info("Login URL :"+prop.getProperty("loginUrl"));
            propertyValues.put("loginUrl",loginUrl);

            logger.info("Position URL :"+prop.getProperty("positionUrl"));
            propertyValues.put("positionUrl",positionUrl);

            logger.info("Interview URL :"+prop.getProperty("interviewUrl"));
            propertyValues.put("interviewUrl",interviewUrl);

            logger.info("Auth Header :"+prop.getProperty("authHeader"));
            propertyValues.put("authHeader",authHeader);

            logger.info("Resume Upload Url :"+prop.getProperty("uploadResumeUrl"));
            propertyValues.put("uploadResumeUrl",resumeUploadUrl);

            logger.info("CSV file location :"+prop.getProperty("csvFileLoc"));
            propertyValues.put("csvFileLoc",fileLoc);

            logger.info("File Name prefix :"+prop.getProperty("fileNamePrefix"));
            propertyValues.put("fileNamePrefix",fileNamePrefix);



        } catch (Exception ex) {
            logger.error("**********************************************************************************************************************************");
            logger.error("**********************************************************************************************************************************");
            logger.error("*******************ERROR Reading Properties File. Property file entry is essential for this jar to run. *************************");
            logger.error("**********************************************************************************************************************************");
            logger.error("**********************************************************************************************************************************");
            logger.error("Exception is",ex);
            logger.error("Exiting System. Please use a valid properties file and retry. If issue persist contact Developer");
            System.exit(1);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info(" Properties Read from file--------end" );
        return propertyValues;
    }

}

