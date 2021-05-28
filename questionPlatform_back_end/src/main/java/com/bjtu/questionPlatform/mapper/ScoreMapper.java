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
            "values (#{expertName},#{judgementId},#{reportId},#{ID},#{score})")
    void createScore(Score score);

    @Insert("insert into totalscore (expertName,reportId,suggestion,totalscore) "+
            "values (#{expertName},#{reportId},#{suggestion},#{totalscore})")
    void createTotalScore(Score score);
}
