package com.demo.mybatis_use.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.demo.mybatis_use.config.EntityConfig;
import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.pojo.Student;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@MapperScan("com.demo.mybatis_use.mapper")
class StudentMapperTest {


    @Resource
    private StudentMapper studentMapper;
    @Test
    void testSelectAllStudents(){
        for (Student student : studentMapper.selectAllStudents()) {
            System.out.println(student);
        }
    }
    @Test
    void testSelectStudentsByAny(){
        Student st = new Student();
        st.setSId(4);
        st.setSName("绿");
        MyClass myClass = new MyClass();
        myClass.setCName("2020");
        st.setGrade(myClass);

        for (Student student : studentMapper.selectStudentsByAny(st)) {
            System.out.println(student);
        }
    }
    @Test
    void testDeleteById(){
        int status = studentMapper.deleteById(6);
        System.out.println(status);
    }
    @Test
    void testUpdateByStudent(){

        LambdaUpdateWrapper<Student> studentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        studentLambdaUpdateWrapper.eq(Student :: getSId,5)
                .set(Student::getSName,"小黄")
                .set(Student::getAcj,23)
                .set(Student::getBcj,34)
                .set(Student::getCcj,54)
                .set(Student::getZcj,111)
                .set(Student::getLogo,"e.jpg");
        int update = studentMapper.update(null, studentLambdaUpdateWrapper);
        System.out.println(update);

    }
    @Test
    void testInsertStudent(){
        Student st = Student.builder()
                .sClass(4)
                .sName("小黑5")
                .logo("e2.jpg")
                .Acj(23)
                .Bcj(34)
                .Ccj(54)
                .Zcj(111)
                .grade(new MyClass(1, null,null))
                .build();
        System.out.println(studentMapper.insertStudent(st));
    }
    @Test
    void testSelectStudentByCId(){
        for (Student student : studentMapper.selectStudentByCId(1)) {
            System.out.println(student);
        }

    }
}