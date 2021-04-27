package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.*;

import com.bjtu.questionPlatform.service.ReportService;
import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import com.google.common.collect.ArrayListMultimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final static String rootPath=System.getProperty("user.dir")+"\\files";
    @CrossOrigin
    @ResponseResultBody
    @PostMapping(value = "/upload")
    public void uploadFile(MultipartFile file){
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

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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


        HashMap<String, Object> data = new HashMap<>();
        data.put("keyWord", keyWord);
        data.put("grades", grades);
        data.put("judgement", judgement);
        return data;
    }
}
