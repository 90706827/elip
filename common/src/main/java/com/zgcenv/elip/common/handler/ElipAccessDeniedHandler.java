package com.zgcenv.elip.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.zgcenv.elip.common.model.ElipResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.Line;
import java.io.IOException;

/**
 * @ClassName ElipAccessDeniedHandler
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 21:49
 * @Version 1.0
 **/
@Slf4j
public class ElipAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        log.info("ElipAccessDeniedHandler-没有权限访问该资源");
        ElipResponse elipResponse = new ElipResponse();
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().write(JSONObject.toJSONString(elipResponse.message("没有权限访问该资源")).getBytes());

    }
}
