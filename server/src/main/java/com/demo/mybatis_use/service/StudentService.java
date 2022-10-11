package com.demo.mybatis_use.service;

import com.demo.mybatis_use.pojo.Student;
import com.github.pagehelper.PageInfo;

public interface StudentService {
    //全部查询
    PageInfo<Student> getStudents(int page,int rows);
    //自定义查询
    PageInfo<Student> getStudentsByAny(int page,int rows,Student st);
    //删除student
    void deleteStudentById(int sId);
    //更新学生数据
    void updateStudent(Student st);
    //添加学生
    void addStudent(Student st);


}
