package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.mapper.UserMapper;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.encodeUtils.EncodeUtil;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.token.JWTUtils;
import com.bjtu.questionPlatform.utils.token.JwtUser;
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

    @Override
    public String userLogin(String username, String password) {
        User user = userMapper.selectUserByUserName(username);
        if (user == null || !encodeUtil.verifyEncode(password, user.getMail(), user.getPassword())) {
            throw new DefinitionException(ErrorEnum.ERROR_NICKNAME_OR_PASSWORD);
        }
        JwtUser userDetails = (JwtUser) jwtUserService.loadUserByUsername(username);
        String token = jwtUtils.generateToken(userDetails);
        return token;
    }

    @Override
    public void register(User user) {

    }
}
