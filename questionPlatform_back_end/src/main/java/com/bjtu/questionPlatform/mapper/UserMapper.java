package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.Role;
import com.bjtu.questionPlatform.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User selectUserByUserName(String username);

    @Select("select roleName from user,role,user_role "+
            "where user.username=#{username} "+"" +
            "and user.id=user_role.userID and role.id = user_role.roleID")
    List<Role> selectRoleByUserName(String username);
}
