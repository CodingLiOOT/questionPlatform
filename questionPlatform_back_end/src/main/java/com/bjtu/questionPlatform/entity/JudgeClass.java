package com.bjtu.questionPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class JudgeClass {
    private String JClassId;
    private String JClassName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp JClassTime;
    private String managerId;

    public String getJClassId() {
        return JClassId;
    }

    public void setJClassId(String JClassId) {
        this.JClassId = JClassId;
    }

    public String getJClassName() {
        return JClassName;
    }

    public void setJClassName(String JClassName) {
        this.JClassName = JClassName;
    }

    public Timestamp getJClassTime() {
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
}
