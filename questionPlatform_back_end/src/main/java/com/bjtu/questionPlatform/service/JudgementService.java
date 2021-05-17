package com.bjtu.questionPlatform.service;


import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.Report;

import com.bjtu.questionPlatform.entity.JudgeClass;



import java.util.List;

public interface JudgementService {

    void allocateJudge(Report report);

    List<Judgement> selectJudgementByJClassId(String jClassId);

    void createJClass(JudgeClass judgeClass);
    List<JudgeClass> getAllJClasses();
    List<Judgement> getAllJudgements();
    void createJudgement(Judgement judgement);

}
