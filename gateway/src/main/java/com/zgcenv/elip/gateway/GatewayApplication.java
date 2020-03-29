package com.zgcenv.elip.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName GatewayApplication
 * @Description 网关
 * @Author Mr.Jangni
 * @Date 2020/3/11 10:54
 * @Version 1.0
 **/
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.zgcenv.elip")
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);

    }
}
