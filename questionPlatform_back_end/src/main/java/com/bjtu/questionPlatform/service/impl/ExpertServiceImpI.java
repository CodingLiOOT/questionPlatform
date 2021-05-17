package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.mapper.ExpertMapper;
import com.bjtu.questionPlatform.mapper.UserMapper;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.token.JWTUtils;
import com.bjtu.questionPlatform.utils.token.JwtUser;
import com.bjtu.questionPlatform.utils.verifyCodeUtils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.UUID;
@Service
public class ExpertServiceImpI implements ExpertService {


    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private JwtUserServiceImpl jwtUserService;
    @Autowired
    private VerifyCodeUtils verifyCodeUtils;
    @Autowired
    private ExpertMapper expertMapper;


    @Value("${mail.fromMail.addr}")
    private String from;

    @Value("${mail.fromMail.subject}")
    private String subject;

    @Autowired
    private JavaMailSender mailSender;


    private void send(String to, String link,String sendMessage) {
        Context context = new Context();
        context.setVariable("message", link);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText("您的邀请链接为：" + link +"\n"+"您的邀请信息(邀请码)为"+sendMessage);
        try {
            mailSender.send(message);
        } catch (Exception ignored) {
            System.out.println("发送失败");
        }
    }

    @Override
    public void invite(Expert expert,String link,String message) {

        send(expert.getMail(), link,message);
    }


    @Override
    public void invite(String expertName,String link,String message) {
        Expert expert= expertMapper.selectExpertByUserName(expertName);
        send(expert.getMail(), link,message);
    }

    @Override
    public void createExpert(Expert expert) {//创建新专家
        expertMapper.createExpert(expert);
    }



    @Override
    public Expert selectExpertByExpertName(String expertName) {
        return expertMapper.selectExpertByUserName(expertName);
    }
}
