package com.hongqi.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(value = "com.hongqi.springboot.dao")
public class HongqiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HongqiApplication.class, args);
    }

}
