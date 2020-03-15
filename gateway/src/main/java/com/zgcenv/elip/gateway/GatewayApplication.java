package com.zgcenv.elip.gateway;

import com.zgcenv.elip.common.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @ClassName GatewayApplication
 * @Description 网关
 * @Author Mr.Jangni
 * @Date 2020/3/11 10:54
 * @Version 1.0
 **/
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication(scanBasePackages = "com.zgcenv.elip")
public class GatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GatewayApplication.class, args);

    }
}
