package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.Report;

import java.util.List;

public interface JudgementService {
    void allocateJudge(Report report);

    List<Judgement> selectJudgementByJClassId(String jClassId);
}
