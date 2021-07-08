package com.bjtu.questionPlatform.controller;

<<<<<<< HEAD

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.questionPlatform.entity.*;
import com.bjtu.questionPlatform.service.*;

=======
import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.InviteCodeUtils.InviteCodeUtils;
>>>>>>> 0ce68d9 (feat: 邀请专家和专家登录)
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
<<<<<<< HEAD
    private ScoreService scoreService;
    @Autowired
    private ExpertService expertService;



    //专家登录
    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/expertLogin")
    public HashMap<String, Object> expertLogin(@RequestBody Expert expert) {
        expertService.expertLogin(expert.getExpertName(),expert.getCode());
        HashMap<String, Object> data = new HashMap<>();
        data.put("expert",expertService.selectExpertByExpertName(expert.getExpertName()));
        return data;
    }



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
        data.put("reportStatus",r.getReportStatus());
        return data;
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendScores")
    public void sendScores(@RequestBody Score score) {
        Report report=reportService.selectReportById(score.getReportId());
        System.out.println(score.getReportId());
        // 插入每个score和judgeId
        String judgeWithScore = score.getJudgeWithScore();
        System.out.println("judgeWithScore"+judgeWithScore);
        JSONArray jsonArray = JSON.parseArray(judgeWithScore);

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Score s=new Score();
            // 插入judgement相关数据
            s.setExpertname(score.getExpertname());
            s.setReportId(score.getReportId());
            s.setJudgementid(jsonObject.getString("judgeId"));
            s.setScore(jsonObject.getString("score"));
            s.setID(report.getID());
            scoreService.createScore(s);
        }

        Score totalScore=new Score();
        totalScore.setTotalScore(score.getTotalScore());
        totalScore.setSuggestion(score.getSuggestion());
        totalScore.setReportId(score.getReportId());
        totalScore.setExpertname(score.getExpertname());
        scoreService.createTotalScore(totalScore);

    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getScoreDetails")
    public HashMap<String, Object> getScoreDetails(@RequestBody Report report) {


        List<HashMap<String, Object>> judgements = new ArrayList<>();
        HashMap<String, Object> jClass = new HashMap<>();

        Report r=reportService.selectReportById(report.getReportId());

        //获取打分情况
        List<TotalScore> sc=reportService.selectTotalScoreByReportId(r.getReportId());
        String totalScore=sc.get(0).getTotalscore();
        String suggestion=sc.get(0).getSuggestion();

        // 获取报告pdf内容
        String url="localhost:8090/static/"+r.getReportPath();
        // 获取该报告的jclass
        JudgeClass judgeClass= judgementService.getjClass(r.getjClassId());
        List<Judgement> j=judgementService.getJudgementByJClassId(r.getjClassId());
        List<HashMap<String, Object>> keyWord = new ArrayList<>();

        //获取关键词
        List<KeyWord> w=reportService.selectKeyWordByReportId(report.getReportId());
        for (int i = 0; i < w.size(); i++) {
            HashMap<String, Object> word = new HashMap<>();
            word.put("word", w.get(i).getKeysContent());
            keyWord.add(word);
        }


        //获取指标详情
        for (int i = 0; i < j.size(); i++) {
            HashMap<String, Object> judgement = new HashMap<>();
            Score s=expertService.selectScore(report.getReportId(),j.get(i).getJudgementid());
            judgement.put("judgeId", j.get(i).getJudgementid());
            judgement.put("judgeName", j.get(i).getJudgementname());
            judgement.put("judgeContent",j.get(i).getJudgementcontent());
            judgement.put("judgeProportion",j.get(i).getJudgementproportion());
            System.out.println("s"+s);
            judgement.put("score",s.getScore());
            judgements.add(judgement);
        }
        jClass.put("judgement",judgements);
        jClass.put("jClassId",r.getjClassId());
        jClass.put("jClassName",judgeClass.getjClassName());
        jClass.put("managerId",judgeClass.getManagerId());

        HashMap<String, Object> data = new HashMap<>();
        data.put("reportPdf",url);
        data.put("jClass",jClass);
        data.put("totalScore", totalScore);
        data.put("suggestion",suggestion);
        data.put("keyWords",keyWord);
        data.put("reportStatus",r.getReportStatus());


        return data;


=======
    private MailService mailService;

    @Autowired
    private InviteCodeUtils inviteCodeUtils;


    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendCode")
    public void sendVerifyCode(@RequestBody String expertName) {
        String expertCode =inviteCodeUtils.setCode(expertName); // 根据专家姓名发送6位邀请码，有效时间6小时
        expertService.invite(expertName,"123.com",expertCode);
>>>>>>> 0ce68d9 (feat: 邀请专家和专家登录)
    }


    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getReportList")
    public HashMap<String, Object> getReportList(@RequestBody Expert expert) {
        List<HashMap<String, Object>> reports = new ArrayList<>();
        List<HashMap<String, Object>> keyWords = new ArrayList<>();

        System.out.println(expert.getExpertName());
        List<String> reportIds=reportService.selectReportIdByExpertName(expert.getExpertName());
        for(int i = 0; i <reportIds.size() ; i++) {
            String ReportId;
            ReportId = reportIds.get(i);
            Report report = reportService.selectReportById(ReportId);
            HashMap<String, Object> item = new HashMap<>();
            item.put("reportId", ReportId);
            String n = report.getReportName();
            int dot = n.lastIndexOf('.');
            if ((dot > -1) && (dot < (n.length()))) {
                n = n.substring(0, dot);
            }
            item.put("reportName", n);
            item.put("createTime", report.getReportTime());

            List<KeyWord>w=reportService.selectKeyWordByReportId(ReportId);
            List<HashMap<String, Object>> keyWordsOfTheReport = new ArrayList<>();
            for (int j = 0; j < w.size(); j++) {
                HashMap<String, Object> wordOfTheReport = new HashMap<>();
                wordOfTheReport.put("word", w.get(j).getKeysContent());
                keyWordsOfTheReport.add(wordOfTheReport);

                HashMap<String, Object> word = new HashMap<>();
                word.put("reportId", ReportId);
                word.put("word", w.get(j).getKeysContent());
                keyWords.add(word);

            }
            item.put("keyWord", keyWordsOfTheReport);
            reports.add(item);
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("reports", reports);
        data.put("keyWords", keyWords);
        return data;
    }



<<<<<<< HEAD
=======

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/expertLogin")
    public HashMap<String, Object> expertLogin(@RequestBody String expertName,@RequestBody String code) {
        expertService.expertLogin(expertName,code);
        HashMap<String, Object> data = new HashMap<>();
        data.put("expert",expertService.selectExpertByExpertName(expertName));
        return data;
    }



//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/login")
//    public HashMap<String, Object> loginExpert(@RequestBody Expert expert) {
//        HashMap<String, Object> data = new HashMap<>();
//        data.put("token", userService.userLogin(user));
//        data.put("user",userService.selectUserByUserName(user.getUsername()));
//        data.put("type",userService.selectTypeByUserName(user.getUsername()));
//        return data;
//    }



    

>>>>>>> 0ce68d9 (feat: 邀请专家和专家登录)
}