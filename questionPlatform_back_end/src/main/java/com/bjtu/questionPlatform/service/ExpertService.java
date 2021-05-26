package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.User;

public interface ExpertService {
    void invite(Expert expert,String link);//链接没想好咋写，估计不用写

    void invite(String expertName,String link);//链接没想好咋写，估计不用写


    void createExpert(Expert expert);

    Expert selectExpertByExpertName(String expertName);

    void expertLogin(String expertName,String code);

}
