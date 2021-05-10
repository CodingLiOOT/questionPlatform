package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping(value = "/api/expert")
public class ExpertController {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private MailService mailService;

//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/login")
//    public Pair<String, String> login(@RequestBody User user) {
//        return Pair.of("token", userService.userLogin(user));
//    }

//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/login")
//    public HashMap<String, Object> login(@RequestBody User user) {
//
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("token", userService.userLogin(user));
//        data.put("user", userService.selectUserByUserName(user.getUsername()));
//        return data;
//    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendCode")
    public void sendVerifyCode(@RequestBody Expert expert) {
        mailService.sendMail(expert.getMail());
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/createExpert")
    public void register(@RequestBody Expert expert) {
        expertService.createExpert(expert);
    }
}