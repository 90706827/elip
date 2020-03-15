package com.zgcenv.elip.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.zgcenv.elip.common.model.ElipResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ElipAuthenticationEntryPoint
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 22:12
 * @Version 1.0
 **/
@Slf4j
public class ElipAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.info("ElipAuthenticationEntryPoint-token无效");
        ElipResponse elipResponse = new ElipResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().write(JSONObject.toJSONString(elipResponse.message("token无效")).getBytes());

    }
}
