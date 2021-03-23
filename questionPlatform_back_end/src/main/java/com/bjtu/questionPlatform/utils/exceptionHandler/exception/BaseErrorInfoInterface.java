package com.bjtu.questionPlatform.utils.exceptionHandler.exception;

public interface BaseErrorInfoInterface {

    Integer getResultCode();

    String getResultMsg();

    Object getResultData();
}