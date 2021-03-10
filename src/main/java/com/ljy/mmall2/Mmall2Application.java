package com.ljy.mmall2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.ljy.mmall2.mapper")
public class Mmall2Application {

    public static void main(String[] args) {

        SpringApplication.run(Mmall2Application.class, args);
    }

}
