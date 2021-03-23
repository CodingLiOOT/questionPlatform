package com.bjtu.questionPlatform.utils.resultUtils;

import com.alibaba.fastjson.annotation.JSONType;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.BaseErrorInfoInterface;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: framework
 * @description: result define
 * @author: CodingLiOOT
 * @create: 2021-03-18 16:39
 * @version: 1.0
 **/
@JSONType(orders = {"code","msg","data"})
@Data
@Accessors(fluent = true)
public class Result implements Serializable {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return new Result()
                .code(0)
                .msg("操作成功")
                .data(data);
    }

    public static Result success(Object data, String msg) {
        return new Result()
                .code(0)
                .msg(msg)
                .data(data);
    }

    public static Result success(String msg) {
        return new Result()
                .code(0)
                .msg(msg);
    }

    public static Result fail(BaseErrorInfoInterface error) {
        return new Result()
                .code(1)
                .msg(error.getResultMsg())
                .data(error.getResultData());
    }

    public static Result fail(String msg) {
        return new Result()
                .code(1)
                .msg(msg);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

