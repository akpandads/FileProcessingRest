package com.pearson.uploadresumejar.domain.applications;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDomain {
    private String taleoCandidateEmail;
    private String requisitionNumber;
    private String fileName;
    private String fileContentOriginal;
    private List<String> positionId = new ArrayList<>();
    private List<String> interviewId = new ArrayList<>();
    public String getTaleoCandidateEmail() {
        return taleoCandidateEmail;
    }

    public void setTaleoCandidateEmail(String taleoCandidateEmail) {
        this.taleoCandidateEmail = taleoCandidateEmail;
    }

    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentOriginal() {
        return fileContentOriginal;
    }

    public void setFileContentOriginal(String fileContentOriginal) {
        this.fileContentOriginal = fileContentOriginal;
    }

    public List<String> getPositionId() {
        return positionId;
    }


    public List<String> getInterviewId() {
        return interviewId;
    }


}
