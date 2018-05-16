package com.pingpongx.tcctransaction.redpacket.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@EnableDubbo(scanBasePackageClasses = LaunchApplication.class)
public class LaunchApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LaunchApplication.class);
    }
    
    public static void main(String[] args) {
        SpringApplication.run(LaunchApplication.class, args);
    }
    
}
