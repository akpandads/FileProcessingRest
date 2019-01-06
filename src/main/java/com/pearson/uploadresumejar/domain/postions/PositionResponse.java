package com.pearson.uploadresumejar.domain.postions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "integrationPositions","closingVideo","introVideo" })
public class PositionResponse {
    private String defaultLiveAudioMode;
    private transient Object closingVideo;
    private String openvueCode;
    private String modifyDate;
    private Creator creator;
    private Boolean audioOnly;
    private String openvueMode;
    private String ratingMode;
    private Boolean isDraft;
    private Integer retryAllowance;
    private String defaultAddress;
    private String timezone;
    private transient IntegrationPositions integrationPositions;
    private String id;
    private String archived;
    private String title;
    private Boolean isOpenvue;
    private String createDate;
    private Boolean isAutoShared;
    private String dueDate;
    private Theme theme;
    private String interviewType;
    private Boolean isSample;
    private Boolean isArchived;
    private String publicTitle;
    private Integer ratingMax;
    private String defaultCandidateInstructions;
    private String pocPhone;
    private String interviewDurationMinutes;
    private String pocEmail;
    private String pocUrl;
    private String pocName;
    private String visibility;
    private String dataStorageRuleset;
    private Boolean defaultLiveVideoEnabled;
    private Boolean useMcsPoints;
    private Account account;
    private String candidateRulesEnabled;
    private String language;
    private Boolean disablePoc;
    private Boolean recordWhiteboard;
    private Boolean defaultLiveIsRecorded;
    private Boolean isBulkUploadInProgress;
    private Team team;
    private transient Object introVideo;
    private Boolean isExpired;

    public String getDefaultLiveAudioMode() {
        return defaultLiveAudioMode;
    }

    public void setDefaultLiveAudioMode(String defaultLiveAudioMode) {
        this.defaultLiveAudioMode = defaultLiveAudioMode;
    }

    public Object getClosingVideo() {
        return closingVideo;
    }

    public void setClosingVideo(String closingVideo) {
        this.closingVideo = closingVideo;
    }

    public String getOpenvueCode() {
        return openvueCode;
    }

    public void setOpenvueCode(String openvueCode) {
        this.openvueCode = openvueCode;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Boolean getAudioOnly() {
        return audioOnly;
    }

    public void setAudioOnly(Boolean audioOnly) {
        this.audioOnly = audioOnly;
    }

    public String getOpenvueMode() {
        return openvueMode;
    }

    public void setOpenvueMode(String openvueMode) {
        this.openvueMode = openvueMode;
    }

    public String getRatingMode() {
        return ratingMode;
    }

    public void setRatingMode(String ratingMode) {
        this.ratingMode = ratingMode;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    public Integer getRetryAllowance() {
        return retryAllowance;
    }

    public void setRetryAllowance(Integer retryAllowance) {
        this.retryAllowance = retryAllowance;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(String defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public IntegrationPositions getIntegrationPositions() {
        return integrationPositions;
    }

    public void setIntegrationPositions(IntegrationPositions integrationPositions) {
        this.integrationPositions = integrationPositions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    public String getPublicTitle() {
        return publicTitle;
    }

    public void setPublicTitle(String publicTitle) {
        this.publicTitle = publicTitle;
    }

    public Integer getRatingMax() {
        return ratingMax;
    }

    public void setRatingMax(Integer ratingMax) {
        this.ratingMax = ratingMax;
    }

    public String getDefaultCandidateInstructions() {
        return defaultCandidateInstructions;
    }

    public void setDefaultCandidateInstructions(String defaultCandidateInstructions) {
        this.defaultCandidateInstructions = defaultCandidateInstructions;
    }

    public String getPocPhone() {
        return pocPhone;
    }

    public void setPocPhone(String pocPhone) {
        this.pocPhone = pocPhone;
    }

    public String getInterviewDurationMinutes() {
        return interviewDurationMinutes;
    }

    public void setInterviewDurationMinutes(String interviewDurationMinutes) {
        this.interviewDurationMinutes = interviewDurationMinutes;
    }

    public String getPocEmail() {
        return pocEmail;
    }

    public void setPocEmail(String pocEmail) {
        this.pocEmail = pocEmail;
    }

    public String getPocUrl() {
        return pocUrl;
    }

    public void setPocUrl(String pocUrl) {
        this.pocUrl = pocUrl;
    }

    public String getPocName() {
        return pocName;
    }

    public void setPocName(String pocName) {
        this.pocName = pocName;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getDataStorageRuleset() {
        return dataStorageRuleset;
    }

    public void setDataStorageRuleset(String dataStorageRuleset) {
        this.dataStorageRuleset = dataStorageRuleset;
    }

    public Boolean getDefaultLiveVideoEnabled() {
        return defaultLiveVideoEnabled;
    }

    public void setDefaultLiveVideoEnabled(Boolean defaultLiveVideoEnabled) {
        this.defaultLiveVideoEnabled = defaultLiveVideoEnabled;
    }

    public Boolean getUseMcsPoints() {
        return useMcsPoints;
    }

    public void setUseMcsPoints(Boolean useMcsPoints) {
        this.useMcsPoints = useMcsPoints;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCandidateRulesEnabled() {
        return candidateRulesEnabled;
    }

    public void setCandidateRulesEnabled(String candidateRulesEnabled) {
        this.candidateRulesEnabled = candidateRulesEnabled;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Boolean getDisablePoc() {
        return disablePoc;
    }

    public void setDisablePoc(Boolean disablePoc) {
        this.disablePoc = disablePoc;
    }

    public Boolean getRecordWhiteboard() {
        return recordWhiteboard;
    }

    public void setRecordWhiteboard(Boolean recordWhiteboard) {
        this.recordWhiteboard = recordWhiteboard;
    }

    public Boolean getDefaultLiveIsRecorded() {
        return defaultLiveIsRecorded;
    }

    public void setDefaultLiveIsRecorded(Boolean defaultLiveIsRecorded) {
        this.defaultLiveIsRecorded = defaultLiveIsRecorded;
    }

    public Boolean getBulkUploadInProgress() {
        return isBulkUploadInProgress;
    }

    public void setBulkUploadInProgress(Boolean bulkUploadInProgress) {
        isBulkUploadInProgress = bulkUploadInProgress;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Object getIntroVideo() {
        return introVideo;
    }

    public void setIntroVideo(String introVideo) {
        this.introVideo = introVideo;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
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

    public Boolean getOpenvue() {
        return isOpenvue;
    }

    public void setOpenvue(Boolean openvue) {
        isOpenvue = openvue;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Boolean getAutoShared() {
        return isAutoShared;
    }

    public void setAutoShared(Boolean autoShared) {
        isAutoShared = autoShared;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public Boolean getSample() {
        return isSample;
    }

    public void setSample(Boolean sample) {
        isSample = sample;
    }
}
