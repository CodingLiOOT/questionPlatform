package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: questionPlatform_back_end
 * @description: test
 * @author: CodingLiOOT
 * @create: 2021-03-28 15:37
 * @version: 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/test")
public class TestController {

    @Autowired
    private MailService mailService;

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/mail")
    public void testMail(){
        mailService.sendMail("18301078@bjtu.edu.cn");
    }
}
