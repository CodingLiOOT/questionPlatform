package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.KeyWord;
import com.bjtu.questionPlatform.entity.Report;
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

    @Select("select * from report where username = #{username}")
    Report selectReportByUsername(String username);

    @Select("select * from report")
    List<Report> getAllReports();

    @Select("select * from KeyWord")
    List<KeyWord> getAllKeyWords();

    @Select("select * from KeyWord")
    List<KeyWord> selectKeyWordByReportId(String reportId);

    @Insert("insert into report (reportId,username,reportPath,reportTime,reportName) "+
            "values (#{reportId},#{username},#{reportPath},NOW(),#{reportName})")
    void createReport(Report report);

    @Insert("insert into keyWord (keysId,reportId,keysContent,keysTime) "+
            "values (#{keysId},#{reportId},#{keysContent},NOW())")
    void createKey(KeyWord keyWord);

}
