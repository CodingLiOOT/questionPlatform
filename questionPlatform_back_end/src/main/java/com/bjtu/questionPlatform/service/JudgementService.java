package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.JudgeClass;
import com.bjtu.questionPlatform.entity.Judgement;

import java.util.List;

public interface JudgementService {
    void createJClass(JudgeClass judgeClass);
    List<JudgeClass> getAllJClasses();
    List<Judgement> getAllJudgements();
    void createJudgement(Judgement judgement);
}
