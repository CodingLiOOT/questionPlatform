package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.Score;

public interface ExpertService {



    void createExpert(Expert expert);

    Expert selectExpertByExpertName(String expertName);

    Score selectScore(String reportId,String judgementId);

    void invite(Expert expert,String link);//链接没想好咋写，估计不用写

    void invite(String expertName,String link);//链接没想好咋写，估计不用写


    void expertLogin(String expertName,String code);

    void inviteExpert(String expertName,String reportId);

}
