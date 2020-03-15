package com.zgcenv.elip.auth.configure;

import com.zgcenv.elip.auth.service.impl.ElipUserDetailsService;
import com.zgcenv.elip.common.filter.ValidateCodeFilter;
import com.zgcenv.elip.common.utils.ParamsConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @ClassName SecurityConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/5 20:05
 * @Version 1.0
 **/
@Slf4j
@Order(2)
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Resource
    private ElipUserDetailsService elipUserDetailsService;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("SecurityConfigure-authenticationManagerBean");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("SecurityConfigure-configure-http");
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                .antMatchers(ParamsConstant.URL_OAUTH_ALL)
                .and()
                .authorizeRequests()
                .antMatchers(ParamsConstant.URL_OAUTH_ALL).authenticated()
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("SecurityConfigure-configure-auth");
        auth.userDetailsService(elipUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}
