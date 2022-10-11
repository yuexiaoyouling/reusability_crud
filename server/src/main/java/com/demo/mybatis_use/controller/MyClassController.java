package com.demo.mybatis_use.controller;

import com.demo.mybatis_use.pojo.Msg;
import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.service.MyClassService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class MyClassController {

    @Resource
    private MyClassService myClassService;
    @GetMapping("/class")
    public Msg searchAllClass(){
        List<MyClass> myClasses = myClassService.selectAllClass();
        System.out.println(myClasses);
        Msg msg = Msg.success();
        msg.add("grades",myClasses);
        return msg;
    }
}
