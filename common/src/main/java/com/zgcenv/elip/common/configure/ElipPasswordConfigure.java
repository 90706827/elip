package com.zgcenv.elip.common.configure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName ElipPassrowdConfigure
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/11 23:41
 * @Version 1.0
 **/
public class ElipPasswordConfigure {

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        String password = "123456";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(password));
        System.out.println(encoder.encode(password));
    }

}
