package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.utils.verifyCodeUtils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Random;

/**
 * @program: questionPlatform_back_end
 * @description: mail service impl
 * @author: CodingLiOOT
 * @create: 2021-03-28 15:15
 * @version: 1.0
 **/
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Value("${mail.fromMail.subject}")
    private String subject;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    VerifyCodeUtils verifyCodeUtils;

    private void send(String to, String verifyCode) {
        Context context = new Context();
        context.setVariable("verifyCode", verifyCode);

        String emailContent = templateEngine.process("emailTemplate", context);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText("您的验证码为：" + verifyCode + ", 有效时间为5分钟");
        try {
            mailSender.send(message);
        } catch (Exception e) {

        }
    }

    @Override
    @Async
    public void sendMail(String to) {
        String code = verifyCodeUtils.generateCode(to);
        send(to, code);
    }
}
