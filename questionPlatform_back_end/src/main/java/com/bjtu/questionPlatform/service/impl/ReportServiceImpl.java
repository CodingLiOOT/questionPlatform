package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.entity.*;
import com.bjtu.questionPlatform.mapper.ReportMapper;
import com.bjtu.questionPlatform.mapper.UserMapper;
import com.bjtu.questionPlatform.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public void upload(Report report) {

    }

    @Override
    public List<KeyWord> selectKeyWordByReportId(String reportId) {
        return reportMapper.selectKeyWordByReportId(reportId);
    }
    @Override
    public List<Grade> selectGradesByReportId(String reportId){
        return reportMapper.selectGradesByReportId(reportId);
    }
    @Override
    public List<Score> selectScoreByReportId(String reportId){
        return reportMapper.selectScoreByReportId(reportId);
    }
    @Override
    public List<Judgement> selectJudgementByJudgementId(String judgementid){
        return reportMapper.selectJudgementByJudgementId(judgementid);
    }

    @Override
    public List<KeyWord> getAllKeyWords() {
        return reportMapper.getAllKeyWords();
    }

    @Override
    public void createKey(KeyWord keyWord) {
        reportMapper.createKey(keyWord);
    }

    @Override
    public Report selectReportByUsername(String username) {
        return reportMapper.selectReportByUsername(username);
    }

    @Override
    public List<Report> getAllReports() {
        return reportMapper.getAllReports();
    }

    @Override
    public void createReport(Report report) {
        reportMapper.createReport(report);
    }
}
