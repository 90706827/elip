package com.zgcenv.elip.common.configure;

import com.zgcenv.elip.common.utils.ParamsConstant;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

/**
 * @ClassName ElipOAuth2FeignConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/7 21:22
 * @Version 1.0
 **/
@Slf4j
public class ElipOauthFeignConfigure {

    @Bean
    public RequestInterceptor requestInterceptor() {

        return requestTemplate -> {
            log.info("ElipOauthFeignConfigure-requestInterceptor");
            // 请求头中添加 Gateway Token
            String zuulToken = new String(Base64Utils.encode(ParamsConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(ParamsConstant.GATEWAY_TOKEN_HEADER, zuulToken);
            // 请求头中添加原请求头中的 Token
            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

            String authorizationToken = details.getTokenValue();
            requestTemplate.header(HttpHeaders.AUTHORIZATION, ParamsConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
        };
    }
}
