package com.bjtu.questionPlatform.service.impl;

import com.bjtu.questionPlatform.mapper.JudgementMapper;
import com.bjtu.questionPlatform.service.JudgementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JudgementServiceImpl implements JudgementService {
    @Autowired
    private JudgementMapper judgementMapper;
}
