package com.bjtu.questionPlatform.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bjtu.questionPlatform.entity.KeyWord;
import com.bjtu.questionPlatform.entity.Report;
import com.bjtu.questionPlatform.entity.User;

import com.bjtu.questionPlatform.service.ReportService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.IOException;
import java.util.*;


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
    private final static String rootPath=System.getProperty("user.dir")+"\\files";
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
//
//    @CrossOrigin
//    @ResponseResultBody
//    @PostMapping(value = "/createReport")
//    public void createReport(@RequestBody Report report){
//        System.out.println("创建新的报告后端测试");
//        // 给word一个id
//        List<KeyWord>keyWords=reportService.getAllKeyWords();
//        int total=keyWords.size();
//        total++; //从1开始
//
//        JSONObject[] keyWord=report.getKeyWord();
//
//        for(int i=0;i<keyWord.length;i++){
//            KeyWord key=new KeyWord();
//            key.setKeysContent(keyWord[i].getString("word"));
//            key.setKeysId(total+"");
//            key.setReportId(report.getReportId()+"");
//            reportService.createKey(key);
//            total++;
//        }
//    }

    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/getList")
    public HashMap<String, Object> getList(@RequestBody User user) {
        List<HashMap<String, Object>> reports = new ArrayList<>();
        List<HashMap<String, Object>> keyWords = new ArrayList<>();

        List<Report> reportlist=reportService.selectReportByUserId(user.getID());
        for (int i = 0; i <reportlist.size() ; i++) {
            HashMap<String, Object> item = new HashMap<>();
            String ReportId;
            ReportId = reportlist.get(i).getReportId();
            item.put("reportId", ReportId);
            item.put("reportName", reportlist.get(i).getReportName());
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

        List<KeyWord>w=reportService.selectKeyWordByReportId(report.getReportId());
        for (int i = 0; i < w.size(); i++) {
            HashMap<String, Object> word = new HashMap<>();
            System.out.println(w.get(i).getKeysContent());
            word.put("word", w.get(i).getKeysContent());
            keyWord.add(word);
        }


        String[] e = {"lily", "ted", "robin"};
        int[] t = {90, 80, 89};
        String[] s = {"aa", "bb", "cc"};
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> item = new HashMap<>();
            item.put("expertName", e[i]);
            item.put("totalScore", t[i]);
            item.put("suggestion", s[i]);
            grades.add(item);
        }

        String[] a = {"lily", "ted", "robin"};
        String[] b = {"a", "b", "c"};
        String[] c = {"aa", "bb", "cc"};
        float[] d = {1, 2, 3};
        for(int j=0;j<3;j++){
            for (int i = 0; i < 3; i++) {
                HashMap<String, Object> item = new HashMap<>();
                item.put("expertName", a[j]);
                item.put("judgementName", b[i]);
                item.put("judgementContent", c[i]);
                item.put("score", d[i]);
                judgement.add(item);
            }
        }


        HashMap<String, Object> data = new HashMap<>();
        data.put("keyWord", keyWord);
        data.put("grades", grades);
        data.put("judgement", judgement);
        return data;
    }
}
