package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.ReportJClass;

import java.util.List;

public interface JudgementService {
    void allocateJudge(ReportJClass reportJClass);

    List<Judgement> selectJudgementByJClassId(String JClassId);
}
