package com.bjtu.questionPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class KeyWord {
    String keysId;
    String reportId;
    String keysContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp keysTime;

    public String getKeysId() {
        return keysId;
    }

    public void setKeysId(String keysId) {
        this.keysId = keysId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getKeysContent() {
        return keysContent;
    }

    public void setKeysContent(String keysContent) {
        this.keysContent = keysContent;
    }

    public Timestamp getKeysTime() {
        return keysTime;
    }

    public void setKeysTime(Timestamp keysTime) {
        this.keysTime = keysTime;
    }
}
