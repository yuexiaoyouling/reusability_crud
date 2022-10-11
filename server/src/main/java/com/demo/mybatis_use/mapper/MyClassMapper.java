package com.demo.mybatis_use.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.pojo.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface MyClassMapper extends BaseMapper<MyClass> {


    @Select("select * from class where c_id=#{cId}")
    MyClass selectClassById(int cId);

    @Results(id="class_Map1",value = {
            @Result(id=true,column = "cId",property = "c_id",jdbcType = JdbcType.INTEGER),
            @Result(column = "c_name",property = "cName",jdbcType = JdbcType.VARCHAR),
            @Result(column = "c_id",property = "students",
                    many = @Many(select = "com.demo.mybatis_use.mapper.StudentMapper.selectStudentByCId"))
    })
    @Select("select * from class where c_id=#{cId}")
    MyClass selectClassWithAllStudents(int cId);

}
