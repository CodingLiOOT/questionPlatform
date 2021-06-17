package com.bjtu.questionPlatform.utils.ahpAlgorithm;

import com.bjtu.questionPlatform.utils.redis.JedisInstance;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

public class AhpUtilFactor {
    /**
     * @program: questionPlatform_back_end
     * @description: 重构ahp util ahp算法
     * @author: 童振宇
     * @create: 2021-06-17 14:05
     * @version: 1.0
     *
     **/

    private AhpUtilFactor(){//私有化构造函数
    }

    //定义一个静态枚举类
    enum SingletonEnum {
        //创建一个枚举对象，该对象天生为单例
        INSTANCE;
        private int numOfWeight;//权重个数
        private double[] weightList;


        //私有化枚举的构造函数
        SingletonEnum() {
        }

        public List<Double> getInstance(double[] weight) {
            this.numOfWeight=weightList.length;
            double[][] weightMatrix = new double[numOfWeight][numOfWeight];
            //归一化权重列表
            double[] ciList = new double[numOfWeight];
            //最终权重结果
            double[] finalWeightList = new double[numOfWeight];
            List<Double> finalWeight = new ArrayList<>(numOfWeight);
            for(int i=0;i<numOfWeight;i++){
                double temp=0.0;
                for(int j =0;j<numOfWeight;j++){
                    weightMatrix[i][j]=weightList[i]/weightList[j];
                    temp+= weightMatrix[i][j];
                }
                ciList[i]=temp;
            }

            for(int i=0;i<numOfWeight;i++){
                for(int j =0;j<numOfWeight;j++){
                    weightMatrix[i][j]/= ciList[i];
                }
            }



            for(int i=0;i<numOfWeight;i++){
                finalWeightList[i]=0;
                for(int j =0;j<numOfWeight;j++){
                    finalWeightList[i]+= weightMatrix[j][i];
                }
            }

            double temp=0.0;
            for(int i=0;i<numOfWeight;i++){
                temp+= finalWeightList[i];
            }

            for(int i=0;i<numOfWeight;i++){
                finalWeightList[i]/=temp;
            }

            for (int i = numOfWeight-1; i >= 0; i--) {
                finalWeight.add(finalWeightList[i]);
            }
            return finalWeight;
        }
        }



    public static List<Double> getInstance(double[] weight) {
        return AhpUtilFactor.SingletonEnum.INSTANCE.getInstance(weight);
    }


}
