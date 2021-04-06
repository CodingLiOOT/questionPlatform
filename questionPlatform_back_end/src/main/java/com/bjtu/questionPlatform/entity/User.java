package com.bjtu.questionPlatform.entity;

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

    private String id;

    private String avatar;

    private String username;

    private String password;

    private Timestamp createdDate;

    private String phone;

    private String mail;

    private Integer state;

    private List<Role> roles;
}
