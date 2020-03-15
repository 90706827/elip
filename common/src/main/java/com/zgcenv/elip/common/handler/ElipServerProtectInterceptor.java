package com.zgcenv.elip.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.zgcenv.elip.common.model.ElipResponse;
import com.zgcenv.elip.common.utils.ParamsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName ServerProtectInterceptor
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 22:35
 * @Version 1.0
 **/
@Slf4j
public class ElipServerProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // 从请求头中获取 Gateway Token
        String token = request.getHeader(ParamsConstant.GATEWAY_TOKEN_HEADER);
        String zuulToken = new String(Base64Utils.encode(ParamsConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验 Gateway Token的正确性
        if (StringUtils.equals(zuulToken, token)) {
            log.info("ElipServerProtectInterceptor-通过网关");
            return true;
        } else {
            log.info("ElipServerProtectInterceptor-请通过网关获取资源");
            ElipResponse elipResponse = new ElipResponse();
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getOutputStream().write(JSONObject.toJSONString(elipResponse.message("请通过网关获取资源")).getBytes());
            return false;
        }
    }
}

