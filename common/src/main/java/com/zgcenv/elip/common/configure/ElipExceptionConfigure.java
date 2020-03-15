package com.zgcenv.elip.common.configure;

import com.zgcenv.elip.common.handler.ElipAccessDeniedHandler;
import com.zgcenv.elip.common.handler.ElipAuthenticationEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @ClassName ElipExceptionConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 21:48
 * @Version 1.0
 **/
public class ElipExceptionConfigure {
    @Bean
    @ConditionalOnMissingBean(name = "elipAccessDeniedHandler")
    public ElipAccessDeniedHandler elipAccessDeniedHandler() {
        return new ElipAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "elipAuthenticationEntryPoint")
    public ElipAuthenticationEntryPoint elipAuthenticationEntryPoint() {
        return new ElipAuthenticationEntryPoint();
    }
}
