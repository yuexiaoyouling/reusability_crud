package com.demo.mybatis_use.controller;

import com.demo.mybatis_use.config.EntityConfig;
import com.demo.mybatis_use.pojo.Msg;
import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.pojo.Student;
import com.demo.mybatis_use.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class StudentController {

    @Resource
    private StudentService studentService;


    @GetMapping("/student/{page}/{rows}")
    public Msg searchAllStudents(@PathVariable(value = "page") int page,
                                 @PathVariable(value = "rows") int rows){
        PageInfo<Student> pageInfo = studentService.getStudents(page, rows);
        Msg msg = Msg.success();
        msg.add("pageInfo",pageInfo);
        return msg;
    }
    @GetMapping("/student/{page}/{rows}/{sId}/{sName}/{cName}")
    public Msg searchStudentsByAny(@PathVariable(value = "page" ) int page,
                                   @PathVariable(value = "rows") int rows,
                                   @PathVariable(value = "sId") int sId,
                                   @PathVariable(value = "sName") String sName,
                                   @PathVariable(value = "cName")String cName){
        MyClass myClass = EntityConfig.myClass();
        myClass.setCName(cName);
        Student st = EntityConfig.student();
        st.setSId(sId);
        st.setSName(sName);
        st.setGrade(myClass);
        System.out.println(st);
        PageInfo<Student> pageInfo = studentService.getStudentsByAny(page, rows,st);
        Msg msg = Msg.success();
        msg.add("pageInfo",pageInfo);
        return msg;
    }
    @PostMapping("/student")
    public Msg addStudent(@RequestParam(value = "sClass") int sClass,
                          @RequestParam(value = "sName") String sName,
                          @RequestParam(value = "logo") String logo,
                          @RequestParam(value = "Acj") int Acj,
                          @RequestParam(value = "Bcj") int Bcj,
                          @RequestParam(value = "Ccj") int Ccj,
                          @RequestParam(value = "cId") int cId){
        MyClass myClass = EntityConfig.myClass();
        myClass.setCId(cId);
        Student st = Student.builder()
                .sClass(sClass)
                .sName(sName)
                .logo(logo)
                .Acj(Acj)
                .Bcj(Bcj)
                .Ccj(Ccj)
                .Zcj(Acj+Bcj+Ccj)
                .grade(myClass)
                .build();
        System.out.println(st);
        studentService.addStudent(st);
        return Msg.success();
    }
    @PutMapping("/student")
    public Msg updateStudent(@RequestParam("sId") int sId,
                            @RequestParam("sName") String sName,
                            @RequestParam("Acj") int Acj,
                            @RequestParam("Bcj") int Bcj,
                            @RequestParam("Ccj") int Ccj,
                            @RequestParam("logo") String logo){
        Student st = Student.builder()
                .sId(sId)
                .sName(sName)
                .Acj(Acj)
                .Bcj(Bcj)
                .Ccj(Ccj)
                .Zcj(Acj + Bcj + Ccj)
                .logo(logo)
                .build();
        studentService.updateStudent(st);
        return Msg.success();
    }
    @DeleteMapping("/student/{sId}")
    public Msg removeStudent(@PathVariable("sId") int sId){
        studentService.deleteStudentById(sId);
        return Msg.success();
    }
}
