package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/login")
    public Pair<String, String> login(@RequestBody User user) {
        return Pair.of("token", userService.userLogin(user.getUsername(), user.getPassword()));
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/test")
    @PreAuthorize("hasAnyRole('admin')")
    public String test(@RequestBody User user) {
        return "hello";
    }
}
