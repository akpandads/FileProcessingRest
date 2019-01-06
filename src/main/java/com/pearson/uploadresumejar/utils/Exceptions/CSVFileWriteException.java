package com.pearson.uploadresumejar.utils.Exceptions;

public class CSVFileWriteException extends Exception {
    public CSVFileWriteException(){
        super("Error while writing failed request to CSV files");
    }
}
