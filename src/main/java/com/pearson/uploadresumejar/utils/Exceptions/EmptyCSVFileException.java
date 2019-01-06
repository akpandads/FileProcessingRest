package com.pearson.uploadresumejar.utils.Exceptions;

public class EmptyCSVFileException extends Exception {
    public EmptyCSVFileException (){
        super("CSV file is empty or contains only header with no data ");
    }
}
