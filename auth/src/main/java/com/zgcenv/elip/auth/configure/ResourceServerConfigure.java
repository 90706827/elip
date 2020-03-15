package com.zgcenv.elip.auth.configure;

import com.zgcenv.elip.common.handler.ElipAccessDeniedHandler;
import com.zgcenv.elip.common.handler.ElipAuthenticationEntryPoint;
import com.zgcenv.elip.common.utils.AuthProperties;
import com.zgcenv.elip.common.utils.ParamsConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.annotation.Resource;
import java.util.concurrent.ForkJoinPool;

/**
 * @ClassName ResourceServerConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 20:24
 * @Version 1.0
 **/
@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Resource
    private ElipAccessDeniedHandler elipAccessDeniedHandler;

    @Resource
    private ElipAuthenticationEntryPoint elipAuthenticationEntryPoint;
    @Autowired
    private AuthProperties authProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        log.info("ResourceServerConfigure-configure-http");
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(authProperties.getAnonUrl(), ",");
        log.info(anonUrls[1]);
        http.csrf().disable()
                .requestMatchers().antMatchers(ParamsConstant.URL_ALL)
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers(ParamsConstant.URL_ALL).authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        log.info("ResourceServerConfigure-configure-resources");
        resources.authenticationEntryPoint(elipAuthenticationEntryPoint)
                .accessDeniedHandler(elipAccessDeniedHandler);
    }
}