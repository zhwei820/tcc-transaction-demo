package com.pingpongx.tcctransaction.capital.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubbo(scanBasePackageClasses = LaunchApplication.class)
@ComponentScan(basePackages = {"com.pingpongx.tcctransaction.capital"})
@MapperScan(basePackages = {"com.pingpongx.tcctransaction.capital.mapper"})
@EnableScheduling
public class LaunchApplication{

    public static void main(String[] args) {
        SpringApplication.run(LaunchApplication.class, args);
    }
    
}
