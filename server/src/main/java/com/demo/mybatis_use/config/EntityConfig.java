package com.demo.mybatis_use.config;

import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.pojo.Student;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class EntityConfig {


    @Bean
    public static  Student student(){

        return new Student();
    }
    @Bean
    public static MyClass myClass(){
        return new MyClass();
    }
}
