package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.entity.JudgeClass;
import com.bjtu.questionPlatform.entity.Judgement;
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
}
