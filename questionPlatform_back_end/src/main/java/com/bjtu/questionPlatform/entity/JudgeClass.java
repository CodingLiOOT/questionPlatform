package com.bjtu.questionPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class JudgeClass {
    public String getjClassId() {
        return jClassId;
    }

    public void setjClassId(String jClassId) {
        this.jClassId = jClassId;
    }

    public String getjClassName() {
        return jClassName;
    }

    public void setjClassName(String jClassName) {
        this.jClassName = jClassName;
    }

    public Timestamp getjClassTime() {
        return jClassTime;
    }

    public void setjClassTime(Timestamp jClassTime) {
        this.jClassTime = jClassTime;
    }

    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    private String jClassId;

    private String jClassName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp jClassTime;
    private String judgement;

    private String managerId;



}
