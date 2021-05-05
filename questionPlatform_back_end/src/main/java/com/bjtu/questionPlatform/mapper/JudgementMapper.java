package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.ReportJClass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JudgementMapper {
    @Insert("insert into reportJClass (reportId,JClassId) "+
            "values (#{reportId},#{JClassId})")
    void allocateJudge(ReportJClass reportJClass);

    @Select("select * from Judgement where JClassId = #{JClassId}")
    List<Judgement> selectJudgementByJClassId(String JClassId);
}
