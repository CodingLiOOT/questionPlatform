package com.bjtu.questionPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * @program: framework
 * @description: user entity
 * @author: ChenDiDi
 * @create: 2021-04-26 18:46
 * @version: 1.0
 **/

@Data
public class Report implements Serializable {
    private String reportId;
    private String reportName;
    private List<String> keyWord;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdTime;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<String> getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(List<String> keys) {
        this.keyWord = keys;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}
