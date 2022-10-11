package com.demo.mybatis_use.service.impl;

import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.service.MyClassService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ClassServiceImplTest {

    @Resource
    private MyClassService classService;
    @Test
    void selectAllClass() {
        for (MyClass myClass : classService.selectAllClass()) {
            System.out.println(myClass);
        }
    }
}