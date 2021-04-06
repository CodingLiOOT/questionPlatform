package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.User;

public interface UserService {

    String userLogin(User user);

    void register(User user);
}
