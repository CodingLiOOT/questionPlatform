package com.bjtu.questionPlatform.controller;

import com.bjtu.questionPlatform.entity.Report;

import com.bjtu.questionPlatform.utils.resultUtils.ResponseResultBody;
import com.google.common.collect.ArrayListMultimap;
import lombok.extern.slf4j.Slf4j;
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

//    private final static String rootPath="D:\\Code\\questionPlatform\\questionPlatform\\questionPlatform_back_end\\src\\main\\resources\\files";
     private final static String rootPath="C:\\Users\\王迪\\Documents\\temp\\country";
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


        String[] w = {"石油", "能源", "共享"};
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> word = new HashMap<>();
            word.put("word", w[i]);
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
