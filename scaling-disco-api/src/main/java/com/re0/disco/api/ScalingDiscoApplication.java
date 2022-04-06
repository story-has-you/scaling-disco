package com.re0.disco.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author fangxi created by 2022/4/6
 */
@SpringBootApplication(scanBasePackages = "com.re0.disco")
@MapperScan("com.re0.disco.mapper")
public class ScalingDiscoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScalingDiscoApplication.class, args);
    }

}
