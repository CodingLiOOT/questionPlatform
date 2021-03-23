package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.User;

public interface UserService {

    String userLogin(String username,String password);

    void register(User user);
}
