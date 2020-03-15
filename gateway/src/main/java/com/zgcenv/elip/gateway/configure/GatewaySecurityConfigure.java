package com.zgcenv.elip.gateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ClassName GatewaySecurityConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/12 0:01
 * @Version 1.0
 **/
@EnableWebSecurity
public class GatewaySecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
    
}