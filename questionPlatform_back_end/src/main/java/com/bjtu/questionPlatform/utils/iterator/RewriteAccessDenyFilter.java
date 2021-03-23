package com.bjtu.questionPlatform.utils.iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bjtu.questionPlatform.utils.exceptionHandler.exception.ErrorEnum;
import com.bjtu.questionPlatform.utils.resultUtils.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: framework
 * @description: access deny filter
 * @author: CodingLiOOT
 * @create: 2021-03-18 20:36
 * @version: 1.0
 **/
@Component
public class RewriteAccessDenyFilter implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(Result.fail(ErrorEnum.ACCESS_DENY), SerializerFeature.WriteMapNullValue));
        writer.flush();
        writer.close();

    }
}
