package com.pearson.uploadresumejar.domain.interview;

public class Summary {
    private Integer insightsRankingScaled;
    private Integer completedEvaluationCount;
    private Double averageCodevueScore;
    private Integer questionCount;
    private Boolean candidateRequestedMoreTime;
    private Integer answerCount;
    private String insightsStatus;
    private Integer assignedEvaluationCount;
    private Double averageRating;

    public Integer getInsightsRankingScaled() {
        return insightsRankingScaled;
    }

    public void setInsightsRankingScaled(Integer insightsRankingScaled) {
        this.insightsRankingScaled = insightsRankingScaled;
    }

    public Integer getCompletedEvaluationCount() {
        return completedEvaluationCount;
    }

    public void setCompletedEvaluationCount(Integer completedEvaluationCount) {
        this.completedEvaluationCount = completedEvaluationCount;
    }

    public Double getAverageCodevueScore() {
        return averageCodevueScore;
    }

    public void setAverageCodevueScore(Double averageCodevueScore) {
        this.averageCodevueScore = averageCodevueScore;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public Boolean getCandidateRequestedMoreTime() {
        return candidateRequestedMoreTime;
    }

    public void setCandidateRequestedMoreTime(Boolean candidateRequestedMoreTime) {
        this.candidateRequestedMoreTime = candidateRequestedMoreTime;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }

    public String getInsightsStatus() {
        return insightsStatus;
    }

    public void setInsightsStatus(String insightsStatus) {
        this.insightsStatus = insightsStatus;
    }

    public Integer getAssignedEvaluationCount() {
        return assignedEvaluationCount;
    }

    public void setAssignedEvaluationCount(Integer assignedEvaluationCount) {
        this.assignedEvaluationCount = assignedEvaluationCount;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
}
