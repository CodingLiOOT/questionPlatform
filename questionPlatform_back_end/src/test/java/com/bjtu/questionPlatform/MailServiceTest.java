package com.bjtu.questionPlatform;

import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.utils.verifyCodeUtils.VerifyCodeUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: questionPlatform_back_end
 * @description: mail test
 * @author: CodingLiOOT
 * @create: 2021-03-28 15:27
 * @version: 1.0
 **/
public class MailServiceTest extends FrameworkApplicationTests{

    @Autowired
    MailService mailService;

    @Autowired
    VerifyCodeUtils verifyCodeUtils;
    @Test
    public void sendTemplateMail(){
        mailService.sendMail("18301044@bjtu.edu.cn");
    }

    @Test
    public void verifyCode(){
        String code=verifyCodeUtils.generateCode("18301044@bjtu.edu.cn");
        System.out.println(verifyCodeUtils.verifyCode("18301044@bjtu.edu.cn",code));
    }

}
