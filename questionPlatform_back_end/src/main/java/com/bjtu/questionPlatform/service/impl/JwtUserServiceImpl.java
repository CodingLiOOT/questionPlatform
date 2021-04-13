package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.entity.Role;
import com.bjtu.questionPlatform.mapper.UserMapper;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.token.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: framework
 * @description: userDetailsService impl
 * @author: CodingLiOOT
 * @create: 2021-03-18 20:08
 * @version: 1.0
 **/
@Service
public class JwtUserServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userMapper.selectUserByUserName(s);
        if(user==null){
            throw new DefinitionException(ErrorEnum.ERROR_NICKNAME_OR_PASSWORD);
        }
        List<Role> roles=userMapper.selectRoleByUserName(s);
        user.setRoles(roles);
        List<SimpleGrantedAuthority> collect =new ArrayList<>();
        roles.forEach(role -> collect.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName())));
        return new JwtUser(user.getID(),user.getUsername(), user.getPassword(), user.getState(), collect);
    }
}
