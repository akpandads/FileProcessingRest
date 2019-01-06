package com.pearson.uploadresumejar.utils.Exceptions;

public class MoreThanOneCSVFileFoundException extends Exception{
    public MoreThanOneCSVFileFoundException(String prefix){
        super("More than one csv file found with file name starting with "+prefix+"\n EXPECTED Only one CSV FIle");
    }
}
