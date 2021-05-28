package com.bjtu.questionPlatform.mapper;

import com.bjtu.questionPlatform.entity.JudgeClass;
import com.bjtu.questionPlatform.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreMapper {
    @Insert("insert into score (expertName,judgementId,reportId,ID,score) "+
            "values (#{expertname},#{judgementid},#{reportId},#{ID},#{score})")
    void createScore(Score score);

    @Insert("insert into totalscore (expertName,reportId,suggestion,totalScore) "+
            "values (#{expertname},#{reportId},#{suggestion},#{totalScore})")
    void createTotalScore(Score score);
}
