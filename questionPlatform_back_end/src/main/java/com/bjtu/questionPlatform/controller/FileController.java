package com.bjtu.questionPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.questionPlatform.entity.*;

import com.bjtu.questionPlatform.service.ReportService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import com.bjtu.questionPlatform.entity.Report;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                r.setReportPath(newName);
                r.setUsername(report.getUsername());
                r.setReportId(reportId+"");
                r.setReportName(oldName);
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
    @PostMapping(value = "/getReport")
    public HashMap<String, Object> getReport(@RequestBody Report report) throws MalformedURLException {
        List<HashMap<String, Object>> keyWord = new ArrayList<>();
        List<HashMap<String, Object>> grades = new ArrayList<>();
        List<HashMap<String, Object>> judgement = new ArrayList<>();

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
        URL url = new URL("file:///C:/Users/%E7%8E%8B%E8%BF%AA/Documents/temp/country/questionPlatform/questionPlatform_back_end/files/a78b4509-ae8b-4a7f-9499-d4a14bce8437.pdf");
        System.out.println(url);

        HashMap<String, Object> data = new HashMap<>();
        data.put("keyWord", keyWord);
        data.put("grades", grades);
        data.put("judgement", judgement);
        data.put("file",url);
        return data;
    }
}
