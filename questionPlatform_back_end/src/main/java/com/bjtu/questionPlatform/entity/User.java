package com.bjtu.questionPlatform.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @program: framework
 * @description: user entity
 * @author: CodingLiOOT
 * @create: 2021-03-18 19:36
 * @version: 1.0
 **/
@Data
public class User implements Serializable {

    private Integer loginType;

    private String verifyCode;

    private String ID;

    private String avatar;

    private String username;

    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createdDate;

    private String phone;

    private String mail;

    private Integer state;

    private List<Role> roles;
}
