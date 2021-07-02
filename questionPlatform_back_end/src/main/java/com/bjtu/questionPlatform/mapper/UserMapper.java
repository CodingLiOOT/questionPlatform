package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.Role;
import com.bjtu.questionPlatform.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User selectUserByUserName(String username);

    @Select(value = "select username from user where mail = #{mail} ")
    String selectUsernameByMail(String mail);

    @Select("select roleName from user,role,user_role "+
            "where user.username=#{username} "+"" +
            "and user.id=user_role.userID and role.id = user_role.roleID")
    List<Role> selectRoleByUserName(String username);

    @Select("select * from user where username = #{username} or mail = #{mail}")
    List<User> selectUserByNameOrMail(String username,String mail);

    @Select("select type from user where username = #{username} or mail = #{mail}")
    public String selectTypeByUserName(String username);

    @Insert("insert into user (ID,username,password,createdDate,mail,state,type) "+
            "values (#{ID},#{username},#{password},NOW(),#{mail},1,1)")
    void register(User user);

}
