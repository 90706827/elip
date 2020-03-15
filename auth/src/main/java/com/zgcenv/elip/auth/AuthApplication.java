package com.zgcenv.elip.auth;

import com.zgcenv.elip.common.annotation.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author admin
 */
@EnableElipRedis
@EnableElipExceptionHandler
@EnableElipServerProtect
@EnableEurekaClient
@EnableElipValidateCode
@EnableElipPasswordConfigure
@MapperScan("com.zgcenv.elip.auth.dao")
@SpringBootApplication(scanBasePackages = "com.zgcenv.elip")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

}
