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

    @Insert("insert into report (reportId,ID,reportPath,reportTime,reportName) "+
            "values (#{reportId},#{ID},#{reportPath},NOW(),#{reportName})")
    void upload(Report report);

    @Select("select * from report where username = #{username}")
    List<Report> selectReportByUsername(String username);

    @Select("select * from report")
    List<Report> getAllReports();

    @Select("select * from KeyWord")
    List<KeyWord> getAllKeyWords();

    @Insert("insert into report (reportId,ID,reportPath,reportTime,reportName) "+
            "values (#{reportId},#{ID},#{reportPath},NOW(),#{reportName})")
    void createReport(Report report);

    @Insert("insert into keyWord (keysId,reportId,keysContent,keysTime) "+
            "values (#{keysId},#{reportId},#{keysContent},NOW())")
    void createKey(KeyWord keyWord);

}
