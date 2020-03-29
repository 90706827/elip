package com.zgcenv.elip.official;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.zgcenv.elip")
public class OfficialApplication {

    public static void main(String[] args) {
        SpringApplication.run(OfficialApplication.class, args);
    }

}
