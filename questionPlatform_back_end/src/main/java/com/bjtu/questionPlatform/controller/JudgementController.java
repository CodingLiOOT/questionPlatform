package com.bjtu.questionPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.questionPlatform.entity.*;
import com.bjtu.questionPlatform.service.JudgementService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/newJudgement")
    public Pair getList(@RequestBody JudgeClass j) {
        // 插入指标类基本信息
        List<JudgeClass> js=judgementService.getAllJClasses();
        int num=js.size()+1;
        JudgeClass jc=new JudgeClass();
        jc.setJClassId(num+"");
        System.out.println("classname:"+j.getjClassName());
        System.out.println("managerId:"+j.getManagerId());
        jc.setjClassName(j.getjClassName());
        jc.setManagerId(j.getManagerId());
        judgementService.createJClass(jc);

        // 插入每个judgement
        String judgement=j.getJudgement();
        System.out.println("judgement"+judgement);
        JSONArray jsonArray = JSON.parseArray(judgement);

        List<Judgement>jgts=judgementService.getAllJudgements();
        int total=jgts.size()+1;

        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Judgement judge=new Judgement();
            // 插入judgement相关数据
            judge.setJudgementcontent(jsonObject.getString("judgementcontent"));
            judge.setjClassId(num+"");
            judge.setJudgementname(jsonObject.getString("judgementname"));
            judge.setJudgementproportion(jsonObject.getString("judgementproportion"));
            judge.setManagerid(j.getManagerId());
            judge.setJudgementid(total+"");
            judgementService.createJudgement(judge);
            total++;
        }

        return Pair.of("Status",1);
    }

}
