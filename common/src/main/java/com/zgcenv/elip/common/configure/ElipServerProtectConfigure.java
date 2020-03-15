package com.zgcenv.elip.common.configure;

import com.zgcenv.elip.common.handler.ElipServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName ServerProtectConfigure
 * @Description 服务防护配置
 * @Author Mr.Jangni
 * @Date 2020/3/5 22:22
 * @Version 1.0
 **/
public class ElipServerProtectConfigure implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ElipServerProtectInterceptor());
    }

}
