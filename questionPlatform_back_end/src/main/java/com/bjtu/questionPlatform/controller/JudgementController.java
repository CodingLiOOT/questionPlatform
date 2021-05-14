package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.*;
import com.bjtu.questionPlatform.service.JudgementService;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: questionPlatform_back_end
 * @description: judgement controller
 * @author: nancy_wdi
 * @create: 2021-05-04 11:13
 * @version: 1.0
 **/

@Slf4j
@RestController
@RequestMapping(value = "/api/judgement")
public class JudgementController {
    @Autowired
    private JudgementService judgementService;

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/allocateJudgement")
    public void allocateJudgement(@RequestBody Report report) {
        System.out.println(report.getReportId());
        System.out.println(report.getjClassId());
        //report.setJClassId(1+"");
        judgementService.allocateJudge(report);

    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getOneJudgement")
    public HashMap<String, Object> getOneJudgement(@RequestBody Judgement judgement) {
        System.out.println(judgement.getjClassId());

        List<HashMap<String, Object>> jd = new ArrayList<>();


        List<Judgement> j=judgementService.selectJudgementByJClassId(judgement.getjClassId());
        for(int i=0;i<j.size();i++){
            HashMap<String, Object> item = new HashMap<>();

            item.put("judgementId", j.get(i).getJudgementid());
            item.put("judgementName", j.get(i).getJudgementname());
            item.put("judgementContent", j.get(i).getJudgementcontent());
            item.put("judgementProportion", j.get(i).getJudgementproportion());
            item.put("managerId", j.get(i).getManagerid());

            jd.add(item);

        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("judgement", jd);



        return data;
    }

}
