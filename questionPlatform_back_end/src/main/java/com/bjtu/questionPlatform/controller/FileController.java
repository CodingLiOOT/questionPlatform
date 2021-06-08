package com.bjtu.questionPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.questionPlatform.entity.*;

import com.bjtu.questionPlatform.service.ReportService;
import com.bjtu.questionPlatform.service.UserService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.bjtu.questionPlatform.entity.Report;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.IOException;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;




/**
 * @program: questionPlatform_back_end
 * @description: file controller
 * @author: CodingLiOOT
 * @create: 2021-04-13 19:33
 * @version: 1.0
 **/
@Slf4j
@RestController
@RequestMapping(value = "/api/file")
public class FileController{

    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;


//    private final static String rootPath="D:\\Code\\questionPlatform\\questionPlatform\\questionPlatform_back_end\\src\\main\\resources\\files";
//     private final static String rootPath="C:\\Users\\王迪\\Documents\\temp\\country";
private final static String rootPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\";
    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/upload")
    public void uploadFile(Report report){
        MultipartFile file=report.getFile();
        System.out.println("文件上传后端测试");

        File fileDir = new File(rootPath);
        if(!fileDir.exists()&&!fileDir.isDirectory()){
            fileDir.mkdirs();
        }
        try{
            if(file!=null){
                System.out.println("文件不空");
                String oldName = file.getOriginalFilename();
                System.out.println(oldName);
                String newName =  UUID.randomUUID().toString()+ (oldName != null ? oldName.substring(oldName.lastIndexOf(".")) : null);
                System.out.println(newName);
                file.transferTo(new File(rootPath,newName));

                // 为了获取关键词id
                List<KeyWord>keyWords=reportService.getAllKeyWords();
                int total=keyWords.size()+1;

                // 为了获取报告id
                List<Report>reports=reportService.getAllReports();
                int reportId=reports.size()+1;

                String k=report.getKeyWord();
                JSONArray jsonArray = JSON.parseArray(k);

                Report r=new Report();
                User u=userService.selectUserByUserName(report.getUsername());
                r.setReportPath(newName);
                r.setID(u.getID());
                r.setReportId(reportId+"");
                r.setReportName(oldName);
                r.setReportStatus("1");
                reportService.createReport(r);

                for(int i=0;i<jsonArray.size();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    KeyWord key=new KeyWord();
                    key.setKeysContent(jsonObject.getString("word"));
                    System.out.println("haha"+jsonObject.getString("word"));
                    key.setKeysId(total+"");
                    key.setReportId(reportId+"");
                    System.out.println(reportId);
                    reportService.createKey(key);
                    total++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getList")
    public HashMap<String, Object> getList(@RequestBody User user) {
        List<HashMap<String, Object>> reports = new ArrayList<>();
        List<HashMap<String, Object>> keyWords = new ArrayList<>();

        System.out.println(user.getUsername());
        User u=userService.selectUserByUserName(user.getUsername());
        List<Report> reportlist=reportService.selectReportByUserId(u.getID());
        for (int i = 0; i <reportlist.size() ; i++) {
            HashMap<String, Object> item = new HashMap<>();
            String ReportId;
            ReportId = reportlist.get(i).getReportId();
            item.put("reportId", ReportId);
            String Status=reportlist.get(i).getReportStatus();
            item.put("reportStatus",Status);
            String n=reportlist.get(i).getReportName();
            int dot = n.lastIndexOf('.');
            if ((dot >-1) && (dot < (n.length()))) {
                n=n.substring(0, dot);
            }
            item.put("reportName", n);
            item.put("createTime", reportlist.get(i).getReportTime());

            List<KeyWord>w=reportService.selectKeyWordByReportId(reportlist.get(i).getReportId());
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

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getReport")
    public HashMap<String, Object> getReport(@RequestBody Report report) {
        List<HashMap<String, Object>> keyWord = new ArrayList<>();
        List<HashMap<String, Object>> grades = new ArrayList<>();
        List<HashMap<String, Object>> judgement = new ArrayList<>();

        System.out.println("获取某一报告"+report.getReportId());
        System.out.println("报告地址"+report.getReportName());



        List<KeyWord>w=reportService.selectKeyWordByReportId(report.getReportId());
        for (int i = 0; i < w.size(); i++) {
            HashMap<String, Object> word = new HashMap<>();
            //System.out.println(w.get(i).getKeysContent());
            word.put("word", w.get(i).getKeysContent());
            keyWord.add(word);
        }

        List<Grade> g=reportService.selectGradesByReportId(report.getReportId());
        for (int i = 0; i <g.size() ; i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("expertName", g.get(i).getExpertName());
            item.put("totalScore", g.get(i).getTotalScore());
            item.put("suggestion", g.get(i).getSuggestion());
            grades.add(item);
        }

        List<Score> s=reportService.selectScoreByReportId(report.getReportId());
        for(int i=0;i<s.size();i++){
            List<Judgement> j=reportService.selectJudgementByJudgementId(s.get(i).getJudgementid());
            HashMap<String, Object> item = new HashMap<>();
            item.put("expertName", s.get(i).getExpertname());
            item.put("judgementName", j.get(0).getJudgementname());
            item.put("judgementContent", j.get(0).getJudgementcontent());
            item.put("score", s.get(i).getScore());
            judgement.add(item);

        }

        Report rpt= reportService.selectReportById(report.getReportId());
        System.out.println("id"+report.getReportId());
        String url="http://localhost:8090/static/"+rpt.getReportPath();

        HashMap<String, Object> data = new HashMap<>();
        data.put("keyWord", keyWord);
        data.put("grades", grades);
        data.put("judgement", judgement);

        Report r=reportService.selectReportById(report.getReportId());
        data.put("reportStatus",r.getReportStatus());

        data.put("file",url);

        String Status= rpt.getReportStatus();
        data.put("reportStatus",Status);

        return data;
    }
}
