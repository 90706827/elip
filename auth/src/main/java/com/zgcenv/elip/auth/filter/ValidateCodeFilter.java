package com.zgcenv.elip.auth.filter;

import com.alibaba.fastjson.JSONObject;
import com.zgcenv.elip.common.exception.ValidateCodeException;
import com.zgcenv.elip.common.model.ElipResponse;
import com.zgcenv.elip.common.service.ValidateCodeService;
import com.zgcenv.elip.common.utils.ParamsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Nonnull;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName ValidateCodeFilter
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 21:32
 * @Version 1.0
 **/
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest httpServletRequest, @Nonnull HttpServletResponse response, @Nonnull FilterChain filterChain) throws ServletException, IOException {

        log.info("ValidateCodeFilter-doFilterInternal");
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String clientId = getClientId(header, httpServletRequest);
        log.info("ValidateCodeFilter-doFilterInternal" + header + "|" + clientId);
        log.info(httpServletRequest.getParameter(ParamsConstant.GRANT_TYPE));
        RequestMatcher matcher = new AntPathRequestMatcher(ParamsConstant.OAUTH_TOKEN, HttpMethod.POST.toString());
        if (matcher.matches(httpServletRequest)
                && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter(ParamsConstant.GRANT_TYPE), ParamsConstant.GRANT_TYPE_PASSWORD)
                && !StringUtils.equalsAnyIgnoreCase(clientId, "swagger")) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, response);
            } catch (ValidateCodeException e) {
                ElipResponse elipResponse = new ElipResponse();
                response.setCharacterEncoding("UTF-8");
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getOutputStream().write(JSONObject.toJSONString(elipResponse.message(e.getMessage())).getBytes());
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, response);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String code = httpServletRequest.getParameter(ParamsConstant.VALIDATE_CODE_CODE);
        String key = httpServletRequest.getParameter(ParamsConstant.VALIDATE_CODE_KEY);

        log.info("ValidateCodeFilter-validateCode:key-{} code-{}", key, code);
        validateCodeService.check(key, code);
    }

    private String getClientId(String header, HttpServletRequest request) {
        log.info("ValidateCodeFilter-getClientId");
        String clientId = "";
        try {
            byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
            byte[] decoded;
            decoded = Base64.getDecoder().decode(base64Token);
            String token = new String(decoded, StandardCharsets.UTF_8);
            int delim = token.indexOf(":");
            if (delim != -1) {
                clientId = new String[]{token.substring(0, delim), token.substring(delim + 1)}[0];
            }
        } catch (Exception ignore) {
        }
        return clientId;
    }
}
