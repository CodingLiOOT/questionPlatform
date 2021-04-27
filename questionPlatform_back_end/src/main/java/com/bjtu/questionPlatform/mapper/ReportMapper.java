package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReportMapper {

    @Select("select * from report where reportId = #{reportId}")
    Report selectReportById(String reportId);

    @Select("select * from KeyWord where reportId = #{reportId}")
    List<KeyWord> selectKeyWordByReportId(String reportId);

    @Select("select * from totalscore where reportId = #{reportId}")
    List<Grade> selectGradesByReportId(String reportId);

    @Select("select * from score where reportId = #{reportId}")
    List<Score> selectScoreByReportId(String reportId);

    @Select("select * from judgement where judgementid = #{judgementid}")
    List<Judgement> selectJudgementByJudgementId(String judgementid);

    @Insert("insert into report (reportId,username,reportPath,reportTime,reportName) "+
            "values (#{ID},#{username},#{password},NOW(),#{mail},1)")
    void upload(Report report);

}
