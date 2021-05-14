package com.bjtu.questionPlatform.mapper;

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
    @Insert("insert into JudgeClass (JClassId,JClassName,JClassTime,managerId) "+
            "values (#{JClassId},#{jClassName},NOW(),#{managerId})")
    void createJClass(JudgeClass judgeClass);

    @Insert("insert into judgement (judgementId,judgementContent,managerId,judgementProportion,judgementName,JClassId,judgementtype) "+
            "values (#{judgementid},#{judgementcontent},#{managerid},#{judgementproportion},#{judgementname},#{jClassId},1)")
    void createJudgement(Judgement judgement);

    @Select("select * from JudgeClass")
    List<JudgeClass> getAllJClasses();

    @Select("select * from judgement")
    List<Judgement> getAllJudgements();
}
