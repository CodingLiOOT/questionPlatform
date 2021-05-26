package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/Company")
public class CompanyController {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private MailService mailService;

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendCode")
    public void sendVerifyCode(@RequestBody Expert expert) {
        expertService.invite(expert,"123,com");
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/createExpert")
    public void register(@RequestBody Expert expert) {
        expertService.createExpert(expert);
    }
}
