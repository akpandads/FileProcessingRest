package com.pearson.uploadresumejar.domain.interview;

public class Position {
    private String archived;
    private String title;
    private String ratingMax;
    private Creator creator;
    private String id;
    private String interviewType;
    private Boolean isArchived;
    private String timezone;
    private String ratingMode;
    private String dueDate;
    private Permissions permissions;

    public String getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getRatingMode() {
        return ratingMode;
    }

    public void setRatingMode(String ratingMode) {
        this.ratingMode = ratingMode;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public void setArchived(String archived) {
        this.archived = archived;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRatingMax() {
        return ratingMax;
    }

    public void setRatingMax(String ratingMax) {
        this.ratingMax = ratingMax;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }
}