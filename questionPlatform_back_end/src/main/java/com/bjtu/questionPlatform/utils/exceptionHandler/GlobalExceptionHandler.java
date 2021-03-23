package com.bjtu.questionPlatform.utils.exceptionHandler;

import com.bjtu.questionPlatform.utils.resultUtils.Result;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.DefinitionException;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: framework
 * @description: global exception handler
 * @author: CodingLiOOT
 * @create: 2021-03-18 16:42
 * @version: 1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        return DefinitionExceptionHandler(new DefinitionException(ErrorEnum.BODY_NOT_MATCH));
    }

    @ExceptionHandler(value = DefinitionException.class)
    @ResponseBody
    public ResponseEntity DefinitionExceptionHandler(DefinitionException de) {
        return ResponseEntity
                .status(de.getResultCode())
                .body(Result.fail(de));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public ResponseEntity AccessDeniedExceptionHandler(AccessDeniedException ae){
        return ResponseEntity
                .status(ErrorEnum.ACCESS_DENY.getResultCode())
                .body(Result.fail(ErrorEnum.ACCESS_DENY));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Result> OtherExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(ErrorEnum.INTERNAL_SERVER_ERROR.getResultCode())
                .body(Result.fail(ErrorEnum.INTERNAL_SERVER_ERROR));
    }
}

