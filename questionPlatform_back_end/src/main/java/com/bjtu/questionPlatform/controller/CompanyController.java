package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.Expert;
import com.bjtu.questionPlatform.entity.ExpertReport;
import com.bjtu.questionPlatform.entity.KeyWord;
import com.bjtu.questionPlatform.service.ExpertService;
import com.bjtu.questionPlatform.service.MailService;
import com.bjtu.questionPlatform.service.ReportService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/Company")
public class CompanyController {
    @Autowired
    private ExpertService expertService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private MailService mailService;

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/inviteExpert")
    public void sendVerifyCode(@RequestBody ExpertReport expertReport) {
        Expert e=expertService.selectExpertByExpertName(expertReport.getExpertName());
        System.out.println(e.getMail());
        expertService.invite(e,"123.com");
        System.out.println(expertReport.getExpertName());
        System.out.println(expertReport.getReportId());
        expertService.inviteExpert(expertReport.getExpertName(),expertReport.getReportId());
        reportService.modifyReportStatus(3,expertReport.getReportId());
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getKeyWords")
    public HashMap<String, Object> getKeyWords() {
        HashMap<String, Object> data = new HashMap<>();
        List<KeyWord> keyWordList=expertService.getAllKeys();
        data.put("keys",keyWordList);
        return data;
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/createExpert")
    public void register(@RequestBody Expert expert) {
        expertService.createExpert(expert);
    }
}
