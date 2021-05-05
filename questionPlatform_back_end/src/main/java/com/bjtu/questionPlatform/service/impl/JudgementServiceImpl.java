package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.ReportJClass;
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
    public void allocateJudge(ReportJClass reportJClass) {
        judgementMapper.allocateJudge(reportJClass);
    }

    @Override
    public List<Judgement> selectJudgementByJClassId(String JClassId) {
        return judgementMapper.selectJudgementByJClassId(JClassId);
    }
}
