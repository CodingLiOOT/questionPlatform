package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.mapper.UserMapper;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.encodeUtils.EncodeUtil;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.token.JWTUtils;
import com.bjtu.questionPlatform.utils.token.JwtUser;
import com.bjtu.questionPlatform.utils.verifyCodeUtils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @program: framework
 * @description: user service impl
 * @author: CodingLiOOT
 * @create: 2021-03-18 20:19
 * @version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtUserServiceImpl jwtUserService;
    @Autowired
    private EncodeUtil encodeUtil;
    @Autowired
    private VerifyCodeUtils verifyCodeUtils;

    @Override
    public String userLogin(User user) {
        if (user.getLoginType() == 1) {
            verifyCodeUtils.verifyCode(user.getMail(), user.getVerifyCode());
        } else {
            User userBean = userMapper.selectUserByUserName(user.getUsername());
            if (user == null) {
                encodeUtil.verifyEncode(user.getPassword(), user.getMail(), user.getPassword());
            }
        }
        JwtUser userDetails = (JwtUser) jwtUserService.loadUserByUsername(user.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return token;
    }


    @Override
    public void register(User user) {

    }
}
