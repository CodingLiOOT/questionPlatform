package com.bjtu.questionPlatform.utils.ahpAlgorithm;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: questionPlatform_back_end
 * @description: ahp util ahp算法
 * @author: 童振宇
 * @create: 2021-04-18 14:05
 * @version: 1.0
 *
 **/
public class AhpUtil {

    //参考:https://wenku.baidu.com/view/887355e6b90d6c85ed3ac63c.html
    private int numOfWeight;//权重个数
    private double[] weightList;
    private double[][] weightMatrix;
    private double[] ciList;//归一化权重列表
    private double[] finalWeightList;//最终权重结果
    private List<Double> finalWeight;

//    private static final AhpUtil acw = new AhpUtil();//单例模式

    private AhpUtil(double[] weightList){
        this.weightList=weightList;
        this.numOfWeight=weightList.length;
        this.weightMatrix=new double[numOfWeight][numOfWeight];
        this.ciList=new double[numOfWeight];
        this.finalWeightList=new double[numOfWeight];
        this.finalWeight= new ArrayList<>(numOfWeight);
        for(int i=0;i<numOfWeight;i++){
            double temp=0.0;
            for(int j =0;j<numOfWeight;j++){
                weightMatrix[i][j]=weightList[i]/weightList[j];
                temp+=weightMatrix[i][j];
            }
            ciList[i]=temp;
        }

        for(int i=0;i<numOfWeight;i++){
            for(int j =0;j<numOfWeight;j++){
                weightMatrix[i][j]/=ciList[i];
            }
        }



        for(int i=0;i<numOfWeight;i++){
            finalWeightList[i]=0;
            for(int j =0;j<numOfWeight;j++){
                finalWeightList[i]+=weightMatrix[j][i];
            }
        }

        double temp=0.0;
        for(int i=0;i<numOfWeight;i++){
            temp+=finalWeightList[i];
        }

        for(int i=0;i<numOfWeight;i++){
            finalWeightList[i]/=temp;
        }

        for (int i = numOfWeight-1; i >= 0; i--) {
            finalWeight.add(finalWeightList[i]);
        }
    }


    public List<Double> getWeight(){
        return this.finalWeight;
    }






}
