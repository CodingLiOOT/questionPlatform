package com.bjtu.questionPlatform.service.impl;


import com.bjtu.questionPlatform.entity.Expert;

import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.Report;

import com.bjtu.questionPlatform.entity.JudgeClass;

import com.bjtu.questionPlatform.mapper.JudgementMapper;
import com.bjtu.questionPlatform.service.JudgementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JudgementServiceImpl implements JudgementService {
    @Autowired
    private JudgementMapper judgementMapper;

    @Override
    public List<Expert> getAllExperts() {
        return judgementMapper.getAllExperts();
    }

    @Override
    public List<JudgeClass> getAllJudgeClass() {
        return judgementMapper.getAllJudgeClass();
    }

    @Override
    public void allocateJudge(Report report) {
        judgementMapper.allocateJudge(report);
    }

    @Override
    public List<Judgement> selectJudgementByJClassId(String jClassId) {
        return judgementMapper.selectJudgementByJClassId(jClassId);
    }

    public void createJClass(JudgeClass judgeClass) {
        judgementMapper.createJClass(judgeClass);
    }

    @Override
    public List<JudgeClass> getAllJClasses() {
        return judgementMapper.getAllJClasses();
    }

    @Override
    public List<Judgement> getAllJudgements() {
        return judgementMapper.getAllJudgements();
    }

    @Override
    public void createJudgement(Judgement judgement) {
        judgementMapper.createJudgement(judgement);

    }

    @Override
    public String getjClassName(String jClassId) {
        return judgementMapper.getjClassName(jClassId);
    }

    @Override
    public JudgeClass getjClass(String jClassId) {
        return judgementMapper.getjClass(jClassId);
    }

    @Override
    public List<Judgement> getJudgementByJClassId(String jClassId) {
        return judgementMapper.getJudgementByJClassId(jClassId);
    }
}
