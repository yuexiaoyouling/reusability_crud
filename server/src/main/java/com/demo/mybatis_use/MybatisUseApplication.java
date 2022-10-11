package com.demo.mybatis_use;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.mybatis_use.mapper")
public class MybatisUseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisUseApplication.class, args);
    }

}
