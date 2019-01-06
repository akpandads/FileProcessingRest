package com.pearson.uploadresumejar.utils.Exceptions;

public class PutFailedException extends Exception{
    public PutFailedException(String s){
        super("Error while executing put method for "+s);
    }

}
