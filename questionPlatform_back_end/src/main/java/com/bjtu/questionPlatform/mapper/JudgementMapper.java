package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JudgementMapper {
    @Update("update report set jClassId=#{jClassId} where reportId=#{reportId}")
    void allocateJudge(Report report);

    @Select("select * from Judgement where jClassId = #{jClassId}")
    List<Judgement> selectJudgementByJClassId(String jClassId);
}
