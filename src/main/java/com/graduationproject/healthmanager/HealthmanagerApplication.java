package com.graduationproject.healthmanager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.graduationproject.healthmanager.mapper")
public class HealthmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthmanagerApplication.class, args);
    }

}
