package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.*;

import java.util.List;

public interface ReportService {

    void upload(Report report);

    List<KeyWord> selectKeyWordByReportId(String reportId);

    List<Grade> selectGradesByReportId(String reportId);

    List<TotalScore> selectTotalScoreByReportId(String reportId);
    List<Score> selectScoreByReportId(String reportId);

    List<Judgement> selectJudgementByJudgementId(String judgementid);

    List<KeyWord> getAllKeyWords();

    void createKey(KeyWord keyWord);


    List<Report> selectReportByUserId(String ID);

    List<Report> selectReportByUsername(String username);


    List<Report> getAllReports();

    void createReport(Report report);


    Report selectReportById(String reportId);

    List<String> selectReportIdByExpertName(String ExpertName,int finish);

    void modifyReportStatus(int reportStatus,String reportId);

    void modifyFinishStatus(int finish,String reportId,String expertName);

    List<ExpertReport> getExpertReport(String reportId,int finish);

}
