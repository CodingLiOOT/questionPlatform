package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.entity.Score;
import com.bjtu.questionPlatform.mapper.ScoreMapper;
import com.bjtu.questionPlatform.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;


    @Override
    public void createScore(Score score) {
        scoreMapper.createScore(score);
    }

    @Override
    public void createTotalScore(Score score) {
        scoreMapper.createTotalScore(score);
    }
}
