package com.bjtu.questionPlatform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @program: framework
 * @description: role entity
 * @author: CodingLiOOT
 * @create: 2021-03-18 19:37
 * @version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    private Integer ID;

    private String roleName;

    private String roleDesc;

    private Timestamp createdTime;

    //权限的列表
    private ArrayList<Permission> permissions;
}
