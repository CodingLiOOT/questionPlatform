package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.KeyWord;
import com.bjtu.questionPlatform.entity.Report;

import java.util.List;

public interface ReportService {

    void upload(Report report);

    List<KeyWord> selectKeyWordByReportId(String reportId);

    List<KeyWord> getAllKeyWords();

    void createKey(KeyWord keyWord);

    Report selectReportByUsername(String username);

    List<Report> getAllReports();

    void createReport(Report report);

}
