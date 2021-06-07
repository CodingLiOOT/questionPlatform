package com.bjtu.questionPlatform.mapper;



import com.bjtu.questionPlatform.entity.Expert;

import org.apache.ibatis.annotations.Update;

import com.bjtu.questionPlatform.entity.JudgeClass;
import com.bjtu.questionPlatform.entity.Judgement;
import com.bjtu.questionPlatform.entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface JudgementMapper {

    @Select("select * from Expert")
    List<Expert> getAllExperts();

    @Select("select * from JudgeClass")
    List<JudgeClass> getAllJudgeClass();

    @Select("select jClassName from judgeClass where jClassId=#{jClassId}")
    String getjClassName(String jClassId);

    @Update("update report set jClassId=#{jClassId} where reportId=#{reportId}")
    void allocateJudge(Report report);

    @Select("select * from Judgement where jClassId = #{jClassId}")
    List<Judgement> selectJudgementByJClassId(String jClassId);

    @Insert("insert into JudgeClass (JClassId,JClassName,JClassTime,managerId) "+
            "values (#{jClassId},#{jClassName},NOW(),#{managerId})")
    void createJClass(JudgeClass judgeClass);

    @Insert("insert into judgement (judgementId,judgementContent,managerId,judgementProportion,judgementName,JClassId,judgementtype) "+
            "values (#{judgementid},#{judgementcontent},#{managerid},#{judgementproportion},#{judgementname},#{jClassId},1)")
    void createJudgement(Judgement judgement);

    @Select("select * from JudgeClass")
    List<JudgeClass> getAllJClasses();

    @Select("select * from judgement")
    List<Judgement> getAllJudgements();

    @Select("select * from judgeClass where jClassId=#{jClassId}")
    JudgeClass getjClass(String jClassId);

    @Select("select * from judgement where jClassId=#{jClassId}")
    List<Judgement> getJudgementByJClassId(String jClassId);

}
