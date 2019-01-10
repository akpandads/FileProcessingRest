package com.pearson.uploadresumejar.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

@Component
public class FileConetntDecoder {
    private static final Logger logger = LoggerFactory.getLogger(FileConetntDecoder.class);

    public String decodeString(String decodedString){
        logger.info("File content decode start");
        String encodedBytes="";
        byte[] decodedBytes = Base64.getDecoder().decode(decodedString);
        try(InputStream unzipedStream=new GZIPInputStream(new ByteArrayInputStream(decodedBytes))){
            encodedBytes =Base64.getEncoder().encodeToString(IOUtils.toByteArray(unzipedStream));
        }catch(Exception e){
            logger.error("Exception while decoding file content"+e);
        }
        finally {
            logger.info("Encoded String ::  "+encodedBytes);
            return encodedBytes;
        }
    }
}

