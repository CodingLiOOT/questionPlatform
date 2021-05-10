package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Expert;

public interface ExpertService {
    String invite(Expert expert,String link,String message);//可以给专家发邀请

    String invite(String expertName,String link,String message);//可以给专家发邀请


    void createExpert(Expert expert);

    Expert selectExpertByExpertName(String expertName);
}
