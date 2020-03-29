package com.zgcenv.elip.official.config.jwt;

import com.zgcenv.elip.official.module.ResultJson;
import com.zgcenv.elip.official.utils.RespCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @ClassName JwtAuthenticationEntryPoint
 * @Description 用来解决匿名用户访问无权限资源时的异常
 * @Author Mr.Jangni
 * @Date 2019/10/30 10:16
 * @Version 1.0
 **/

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        logger.info("JwtAuthenticationEntryPoint");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().print(ResultJson.toJson(RespCode.NOT_REQUEST));
    }
}