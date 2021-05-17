package com.bjtu.questionPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class JudgeClass {
    private String JClassId;

    private String jClassName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp JClassTime;
    private String judgement;

    private String managerId;

    public String getJClassId() {
        return JClassId;
    }

    public void setJClassId(String JClassId) {
        this.JClassId = JClassId;
    }


    public String getjClassName() {
        return jClassName;
    }

    public void setjClassName(String jClassName) {
        this.jClassName = jClassName;
    }

    public Timestamp getjClassTime() {

        return JClassTime;
    }

    public void setJClassTime(Timestamp JClassTime) {
        this.JClassTime = JClassTime;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }


    public String getJudgement() {
        return judgement;
    }

    public void setJudgement(String judgement) {
        this.judgement = judgement;
    }

}
