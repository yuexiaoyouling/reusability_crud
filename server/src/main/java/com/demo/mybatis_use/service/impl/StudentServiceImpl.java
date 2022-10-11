package com.demo.mybatis_use.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.demo.mybatis_use.config.EntityConfig;
import com.demo.mybatis_use.exception.BusinessException;
import com.demo.mybatis_use.mapper.StudentMapper;
import com.demo.mybatis_use.pojo.ErrorCodeEnum;
import com.demo.mybatis_use.pojo.Student;
import com.demo.mybatis_use.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;


    @Override
    public PageInfo<Student> getStudents(int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Student> students = studentMapper.selectAllStudents();
        return new PageInfo<>(students,5);
    }

    @Override
    public PageInfo<Student> getStudentsByAny(int page, int rows, Student st) {
        PageHelper.startPage(page,rows);
        List<Student> students = studentMapper.selectStudentsByAny(st);
        return new PageInfo<>(students,5);

    }

    @Override
    public void deleteStudentById(int sId) {
        if(studentMapper.deleteById(sId)!=1){
            throw new BusinessException(ErrorCodeEnum.DELETE_ERROR);
        }

    }

    @Override
    public void updateStudent(Student st) {
        LambdaUpdateWrapper<Student> studentLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        studentLambdaUpdateWrapper.eq(Student :: getSId,st.getSId())
                .set(Student::getSName,st.getSName())
                .set(Student::getAcj,st.getAcj())
                .set(Student::getBcj,st.getBcj())
                .set(Student::getCcj,st.getCcj())
                .set(Student::getZcj,st.getZcj())
                .set(Student::getLogo,st.getLogo());
        if(studentMapper.update(null, studentLambdaUpdateWrapper)!=1){
            throw new BusinessException(ErrorCodeEnum.UPDATE_ERROR);
        }

    }

    @Override
    public void addStudent(Student st) {
        if(studentMapper.insertStudent(st)!=1){
            throw new BusinessException(ErrorCodeEnum.INSERT_ERROR);
        }

    }
}
