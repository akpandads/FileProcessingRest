package com.pearson.uploadresumejar.utils.Exceptions;

public class NoCsvFileFoundException extends Exception{
    public NoCsvFileFoundException(String prefix){
        super("No CSV file found with file name starting with "+prefix);
    }

}
