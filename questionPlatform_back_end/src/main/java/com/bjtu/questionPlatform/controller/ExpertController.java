package com.bjtu.questionPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.questionPlatform.entity.*;
import com.bjtu.questionPlatform.service.*;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/Expert")
public class ExpertController {
    @Autowired
    private ReportService reportService;
    @Autowired
    private JudgementService judgementService;
    @Autowired
    private ScoreService scoreService;

    // 返回报告详情和打分指标
    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getOneReport")
    public HashMap<String, Object> getReportJudge(@RequestBody Report report) {
        List<HashMap<String, Object>> judgements = new ArrayList<>();
        HashMap<String, Object> jClass = new HashMap<>();

        Report r=reportService.selectReportById(report.getReportId());
        // 获取报告pdf内容
        String url="localhost:8090/static/"+r.getReportPath();
        // 获取该报告的jclass
        JudgeClass judgeClass= judgementService.getjClass(r.getjClassId());
        List<Judgement> j=judgementService.getJudgementByJClassId(r.getjClassId());

        for (int i = 0; i < j.size(); i++) {
            HashMap<String, Object> judgement = new HashMap<>();
            judgement.put("judgeId", j.get(i).getJudgementid());
            judgement.put("judgeName", j.get(i).getJudgementname());
            judgement.put("judgeContent",j.get(i).getJudgementcontent());
            judgement.put("judgeProportion",j.get(i).getJudgementproportion());
            judgements.add(judgement);
        }
        jClass.put("judgements",judgements);
        jClass.put("jClassId",r.getjClassId());

        HashMap<String, Object> data = new HashMap<>();
        data.put("reportPdf",url);
        data.put("jClass",jClass);
        return data;
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendScores")
    public void sendScores(@RequestBody Score score) {
        Report report=reportService.selectReportById(score.getReportid());

        // 插入每个score和judgeId
        String judgeWithScore = score.getJudgeWithScore();
        System.out.println("judgeWithScore"+judgeWithScore);
        JSONArray jsonArray = JSON.parseArray(judgeWithScore);

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Score s=new Score();
            // 插入judgement相关数据
            s.setExpertname(score.getExpertname());
            s.setReportid(score.getReportid());
            s.setJudgementid(jsonObject.getString("judgeId"));
            s.setScore(jsonObject.getString("score"));
            s.setID(report.getID());
            scoreService.createScore(s);
        }

        Score totalScore=new Score();
        totalScore.setTotalScore(score.getTotalScore());
        totalScore.setSuggestion(score.getSuggestion());
        totalScore.setReportid(score.getReportid());
        totalScore.setExpertname(score.getExpertname());
        scoreService.createTotalScore(totalScore);

    }


}