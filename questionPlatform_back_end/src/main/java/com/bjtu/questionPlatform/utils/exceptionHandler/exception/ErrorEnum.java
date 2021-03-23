package com.bjtu.questionPlatform.utils.exceptionHandler.exception;

import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public enum ErrorEnum implements BaseErrorInfoInterface, Serializable {
    SUCCESS(200,"成功！"),
    BODY_NOT_MATCH(400,"请求数据格式不符"),
    ACCESS_DENY(403,"您没有访问的权限"),
    DUPLICATE_USERNAME(400,"该用户名已被注册"),
    SIGNATURE_NOT_MATCH(401,"Token验证失败"),
    ERROR_NICKNAME_OR_PASSWORD(401,"用户名或密码错误"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!")
    ;

    private Integer errorCode;
    private String errorMsg;

    @Override
    public Integer getResultCode() {
        return errorCode;
    }

    @Override
    public String getResultMsg() {
        return errorMsg;
    }

    @Override
    public Object getResultData() {
        return null;
    }
}

