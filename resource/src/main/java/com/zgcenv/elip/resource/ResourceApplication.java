package com.zgcenv.elip.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @ClassName Application
 * @Description
 * @Author Mr.Jangni
 * @Date 2020/3/15 17:03
 * @Version 1.0
 **/
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.zgcenv.elip")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

}
