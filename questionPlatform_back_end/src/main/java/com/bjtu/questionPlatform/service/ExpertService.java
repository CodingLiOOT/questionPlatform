package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.User;

public interface ExpertService {
    void invite(Expert expert,String link,String message);//可以给专家发邀请

    void invite(String expertName,String link,String message);//可以给专家发邀请


    void createExpert(Expert expert);

    Expert selectExpertByExpertName(String expertName);

    void expertLogin(String expertName,String code);

}
