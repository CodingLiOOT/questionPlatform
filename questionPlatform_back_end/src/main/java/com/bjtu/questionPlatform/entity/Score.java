package com.bjtu.questionPlatform.entity;

public class Score {
    String expertname;
    String judgementid;
    String reportId;
    String ID;  //上传报告的用户id
    String score;
    String suggestion;  // 专家建议
    String totalScore;  // 加权后总分
    String judgeWithScore;  // 前端传来的打分json

    public String getExpertname() {
        return expertname;
    }

    public void setExpertname(String expertname) {
        this.expertname = expertname;
    }

    public String getJudgementid() {
        return judgementid;
    }

    public void setJudgementid(String judgementid) {
        this.judgementid = judgementid;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getJudgeWithScore() {
        return judgeWithScore;
    }

    public void setJudgeWithScore(String judgeWithScore) {
        this.judgeWithScore = judgeWithScore;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
