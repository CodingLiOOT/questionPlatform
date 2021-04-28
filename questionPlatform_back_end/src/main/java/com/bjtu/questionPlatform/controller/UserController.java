package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @program: framework
 * @description: user controller
 * @author: CodingLiOOT
 * @create: 2021-03-18 20:44
 * @version: 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/login")
    public HashMap<String, Object> login(@RequestBody User user) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("token",userService.userLogin(user));
        data.put("user",userService.selectUserByUserName(user.getUsername()));
        return data;
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendVerifyCode")
    public void sendVerifyCode(@RequestBody User user) {
        mailService.sendMail(user.getMail());
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/register")
    public void register(@RequestBody User user){
        userService.register(user);
    }

}
