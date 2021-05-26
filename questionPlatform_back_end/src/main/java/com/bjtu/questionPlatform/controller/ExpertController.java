package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.InviteCodeUtils.InviteCodeUtils;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping(value = "/api/Company")
public class ExpertController {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private MailService mailService;

    @Autowired
    private InviteCodeUtils inviteCodeUtils;


//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/sendCode")
//    public void sendVerifyCode(@RequestBody String expertName) {
//        String expertCode =inviteCodeUtils.setCode(expertName); // 根据专家姓名发送6位邀请码，有效时间6小时
//        expertService.invite(expertName,"123.com",expertCode);
//    }
//
//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/createExpert")
//    public void register(@RequestBody Expert expert) {
//        expertService.createExpert(expert);
//    }




    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/expertLogin")
    public HashMap<String, Object> expertLogin(@RequestBody String expertName,@RequestBody String code) {
        expertService.expertLogin(expertName,code);
        HashMap<String, Object> data = new HashMap<>();
        data.put("expert",expertService.selectExpertByExpertName(expertName));
        return data;
    }



//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/login")
//    public HashMap<String, Object> loginExpert(@RequestBody Expert expert) {
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("token", userService.userLogin(user));
//        data.put("user",userService.selectUserByUserName(user.getUsername()));
//        data.put("type",userService.selectTypeByUserName(user.getUsername()));
//        return data;
//    }



    

}