package com.bjtu.questionPlatform;

import com.bjtu.questionPlatform.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: questionPlatform_back_end
 * @description: user service test
 * @author: CodingLiOOT
 * @create: 2021-03-25 18:27
 * @version: 1.0
 **/
public class UserServiceTest extends FrameworkApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void loginTest(){
        System.out.println(userService.userLogin("Ljz123hhh","Ljz123hhh"));
    }

}
