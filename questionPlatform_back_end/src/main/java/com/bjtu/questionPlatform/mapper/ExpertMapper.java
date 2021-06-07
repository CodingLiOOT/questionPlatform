package com.bjtu.questionPlatform.mapper;


import com.bjtu.questionPlatform.entity.*;
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


    @Select("select mail from expert where expertName = #{expertName}")
    String selectMailByExpertName(String expertName);
    @Insert("insert into expert (expertName,keysId,expertType,mail,phone,expertUnit,expertInformation) "+
            "values (#{expertName},1,1,#{mail},#{phone},#{expertUnit},#{expertInformation})")
        //插入的专家类型暂时没想好咋写
    void createExpert(Expert expert);

    @Select("select * from score where reportid = #{reportId} and judgementid = #{judgementId}")
    Score selectScore(String reportId, String judgementId);

}
