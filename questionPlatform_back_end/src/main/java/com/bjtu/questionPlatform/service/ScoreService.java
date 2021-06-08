package com.bjtu.questionPlatform.service;

import com.bjtu.questionPlatform.entity.Score;

public interface ScoreService {
    void createScore(Score score);

    void createTotalScore(Score score);
}
