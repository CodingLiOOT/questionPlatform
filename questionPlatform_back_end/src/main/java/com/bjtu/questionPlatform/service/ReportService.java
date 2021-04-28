package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.*;

import java.util.List;

public interface ReportService {

    void upload(Report report);

    List<KeyWord> selectKeyWordByReportId(String reportId);

    List<Grade> selectGradesByReportId(String reportId);

    List<Score> selectScoreByReportId(String reportId);

    List<Judgement> selectJudgementByJudgementId(String judgementid);

    Report selectReportById(String reportId);

    List<KeyWord> getAllKeyWords();

    void createKey(KeyWord keyWord);

    Report selectReportByUsername(String username);

    List<Report> getAllReports();

    void createReport(Report report);

}
