package com.pearson.uploadresumejar.utils.Exceptions;

public class FileWithPrefixNotFoundException extends Exception {
    public FileWithPrefixNotFoundException(String prefix){
        super("NO file found starting with "+prefix);
    }
}
