package com.zgcenv.elip.official.config;

import com.zgcenv.elip.official.config.jwt.JwtAuthenticationEntryPoint;
import com.zgcenv.elip.official.config.jwt.JwtRequestFilter;
import com.zgcenv.elip.official.config.jwt.JwtUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName WebSecurityConfig
 * @Description Security 配置
 * @Author Mr.Jangni
 * @Date 2019/10/22 0:02
 * @Version 1.0
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${requestPath}")
    String requestPath;
    private final static Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    @Autowired
    private JwtUserDetailService jwtUserDetailService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @Author Mr.Jangni
     * @Description 跨域 问题的出现是因为浏览器的同源策略造成的
     * @Date 2019/11/1 14:25
     * @Param [httpSecurity]
     * @Return void
     **/
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //跨站
                .csrf().disable()
                //跨域
                .cors().and()
                .authorizeRequests()
                //OPTIONS请求全部放行
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                //对不认证接口放行
                .antMatchers("/web/**", requestPath + "/**").permitAll()
                // 其他接口全部接受验证
                .anyRequest().authenticated().and()
                // make sure we use stateless session; session won't be used to store user's state.
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
                // 基于token，使用JWT，所以不需要HttpSession
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui.html")
                .antMatchers("/webjars/**")
                .antMatchers("/v2/**")
                .antMatchers("/swagger-resources/**");
    }
}