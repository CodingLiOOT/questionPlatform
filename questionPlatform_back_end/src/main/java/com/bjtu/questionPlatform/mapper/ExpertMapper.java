package com.bjtu.questionPlatform.mapper;


import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.Role;
import com.bjtu.questionPlatform.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ExpertMapper {


    @Select("select * from expert where expertName = #{expertName}")
    Expert selectExpertByUserName(String expertName);
    @Select("select * from expert where keysId = #{keysId}")
    List<User> selectExpertByKeysId(String keysId);
    @Insert("insert into expert (expertName,keysId,expertType,mail,phone,expertUnit,expertInformation) "+
            "values (#{expertName},1,1,#{mail},#{phone},#{expertUnit},#{expertInformation})")
    //插入的专家类型暂时没想好咋写
    void createExpert(Expert expert);

}
