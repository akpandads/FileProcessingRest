package com.pearson.uploadresumejar.utils;

import com.pearson.uploadresumejar.domain.applications.ApplicationDomain;
import com.pearson.uploadresumejar.domain.applications.FailedPutRequests;
import com.pearson.uploadresumejar.domain.interview.InterviewResponseDomain;
import com.pearson.uploadresumejar.utils.Exceptions.CSVFileWriteException;
import com.pearson.uploadresumejar.utils.Exceptions.EmptyCSVFileException;
import com.pearson.uploadresumejar.utils.Exceptions.InvalidCSVFileException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReaderWriter {

    private static final Logger logger = LoggerFactory.getLogger(CsvFileReaderWriter.class);

    public static List<ApplicationDomain> readCsvFile(String filePath) throws Exception   {
        logger.info("Read CSV file start for file name and path "+filePath) ;
        List <ApplicationDomain> applicationDomainList = new ArrayList<>();
        try{
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            CSVParser applicationsCsv = new CSVParser(reader, CSVFormat.DEFAULT
                    .withHeader(CsvFileHeader.TALEO_CANDIDATE_EMAIL_ID, CsvFileHeader.REQUISATION_NUMBER, CsvFileHeader.FILE_NAME, CsvFileHeader.FILE_CONTENT)
                    .withIgnoreHeaderCase()
                    .withTrim());
            for (CSVRecord csvRecord : applicationsCsv) {
                ApplicationDomain applicationDomain = new ApplicationDomain();
                applicationDomain.setTaleoCandidateEmail(csvRecord.get(0));
                applicationDomain.setRequisitionNumber(csvRecord.get(1));
                applicationDomain.setFileName(csvRecord.get(2));
                applicationDomain.setFileContent(csvRecord.get(3));
                applicationDomainList.add(applicationDomain);
            }

        }
        catch (Exception e){
            throw new InvalidCSVFileException("Error while reading CSV File");
        }
        if(applicationDomainList.size()==0 || applicationDomainList.size()==1){
            throw new EmptyCSVFileException();
        }
        applicationDomainList.remove(0);
        logger.info(filePath+"read successful. Total rows read :"+applicationDomainList.size());
        return applicationDomainList;
    }

    public static void writeFailedRequestsToCsvFile(List<FailedPutRequests> failedPutRequestsList) throws CSVFileWriteException{
        String filePath="Failed_Resume_Put.csv";
        CSVPrinter csvPrinter;
        try{
            BufferedWriter outputfile = Files.newBufferedWriter(Paths.get(filePath));
            csvPrinter = new CSVPrinter(outputfile,CSVFormat.DEFAULT
                    .withHeader("Position Id","Interview Id", "Url End Point", "Put Request").withEscape('\"'));
            for(FailedPutRequests failedPutRequests:failedPutRequestsList){

                csvPrinter.printRecord(failedPutRequests.getPostionId(),failedPutRequests.getInterviewId(),failedPutRequests.getUrl(),failedPutRequests.getRequest());
            }
            csvPrinter.flush();

        }
        catch (Exception e){
            logger.error("Errored occurred while writing to csv file",e);
            throw new CSVFileWriteException();
        }
    }
}
