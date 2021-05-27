package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.KeyWord;
import com.bjtu.questionPlatform.entity.Report;
import com.bjtu.questionPlatform.entity.User;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.service.ReportService;
import com.bjtu.questionPlatform.service.UserService;
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
    private ExpertService expertService;
    @Autowired
    private ReportService reportService;

    // 返回报告详情和打分指标
    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/sendCode")
    public void getReportJudge(@RequestBody Report report) {

        
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


}