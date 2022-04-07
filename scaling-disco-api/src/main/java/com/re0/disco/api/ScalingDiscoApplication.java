package com.re0.disco.api;

import com.re0.disco.common.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author fangxi created by 2022/4/6
 */
@SpringBootApplication(scanBasePackages = "com.re0.disco")
public class ScalingDiscoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScalingDiscoApplication.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
