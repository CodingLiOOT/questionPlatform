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
import org.springframework.stereotype.Service;

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

    @Override
    public String invite(Expert expert,String link,String message) {
        String mail=expert.getMail();
        verifyCodeUtils.verifyCode(expert.getMail(), link+"\n"+message);
        JwtUser userDetails = (JwtUser) jwtUserService.loadUserByUsername(expert.getExpertName());
        return jwtUtils.generateToken(userDetails);
    }


    @Override
    public String invite(String expertName,String link,String message) {
        Expert expert= expertMapper.selectExpertByUserName(expertName);
        String mail=expert.getMail();
        verifyCodeUtils.verifyCode(expert.getMail(), link+"\n"+message);
        JwtUser userDetails = (JwtUser) jwtUserService.loadUserByUsername(expert.getExpertName());
        return jwtUtils.generateToken(userDetails);
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
