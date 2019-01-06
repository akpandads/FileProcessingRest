package com.pearson.uploadresumejar.utils;

import com.pearson.uploadresumejar.utils.Exceptions.FileWithPrefixNotFoundException;
import com.pearson.uploadresumejar.utils.Exceptions.MoreThanOneCSVFileFoundException;
import com.pearson.uploadresumejar.utils.Exceptions.NoCsvFileFoundException;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.PrefixFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class DirScanner {

    private static final Logger logger = LoggerFactory.getLogger(DirScanner.class);
    public String getFIleNameToBeRead(String path, String fileNamePrefix) throws NoCsvFileFoundException,FileWithPrefixNotFoundException,MoreThanOneCSVFileFoundException {
        File directory = new File(path);
        File[] files = directory.listFiles();
        List<String> csvFileNamesList = new ArrayList<>();
        logger.info("Case Insensitive Search for Files beginning with : "+fileNamePrefix);
        files = directory.listFiles((FileFilter) new PrefixFileFilter(fileNamePrefix, IOCase.INSENSITIVE));
        if(files.length==0){
            throw new FileWithPrefixNotFoundException(fileNamePrefix);
        }
        for(int i =0;i<files.length;i++){
            if(files[i].getName().endsWith(".csv")){
                csvFileNamesList.add(files[i].getName());
            }
        }
        if(csvFileNamesList.size()==0)
            throw new NoCsvFileFoundException(fileNamePrefix);
        else if(csvFileNamesList.size()>1)
            throw new MoreThanOneCSVFileFoundException(fileNamePrefix);

        logger.info("CSV FIle found  : "+csvFileNamesList.get(0));
        return csvFileNamesList.get(0);
    }


}
