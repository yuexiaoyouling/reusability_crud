package com.demo.mybatis_use.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.pojo.Student;
import com.fasterxml.jackson.databind.JavaType;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {



    @Results(id = "stuMap_1",value={
        @Result(id = true, column = "s_id",property = "sId",jdbcType = JdbcType.INTEGER),
        @Result(column = "s_class",property = "sClass",jdbcType = JdbcType.INTEGER),
        @Result(column = "s_name",property = "sName",jdbcType = JdbcType.INTEGER),
        @Result(column = "s_logo",property = "logo",jdbcType = JdbcType.VARCHAR),
        @Result(column = "s_Acj",property = "Acj",jdbcType = JdbcType.INTEGER),
        @Result(column = "s_Bcj",property = "Bcj",jdbcType = JdbcType.INTEGER),
        @Result(column = "s_Ccj",property = "Ccj",jdbcType = JdbcType.INTEGER),
        @Result(column = "s_Zcj",property = "Zcj",jdbcType = JdbcType.INTEGER),
        @Result(column = "c_id", property = "grade",javaType = MyClass.class,one = @One(select="com.demo.mybatis_use.mapper.MyClassMapper.selectClassById"))
    })
    @Select("select * from student s left outer join class c on s.c_id = c.c_id ")
    List<Student> selectAllStudents(); //查询全部

    //  使用的xml配置文件
    List<Student> selectStudentsByAny(Student st); //自定义查询
    //查询学生通过id
    @Results(id = "stuMap_2",value={
            @Result(id = true, column = "s_id",property = "sId",jdbcType = JdbcType.INTEGER),
            @Result(column = "s_class",property = "sClass",jdbcType = JdbcType.INTEGER),
            @Result(column = "s_name",property = "sName",jdbcType = JdbcType.INTEGER),
            @Result(column = "s_logo",property = "logo",jdbcType = JdbcType.VARCHAR),
            @Result(column = "s_Acj",property = "Acj",jdbcType = JdbcType.INTEGER),
            @Result(column = "s_Bcj",property = "Bcj",jdbcType = JdbcType.INTEGER),
            @Result(column = "s_Ccj",property = "Ccj",jdbcType = JdbcType.INTEGER),
            @Result(column = "s_Zcj",property = "Zcj",jdbcType = JdbcType.INTEGER),
    })
    @Select("select * from student where c_Id=#{cId}")
    List<Student> selectStudentByCId(int cId);

//    使用mybatis_plus完成一下两种操作
//    int DeleteByID(int a);//通过ID删除
//    int UpdateByID(Student st);//自定义更新
    @Insert("insert into student(s_class,s_name,s_logo,s_Acj,s_Bcj,s_Ccj,s_Zcj,c_id) " +
            "values(#{sClass},#{sName},#{logo},#{Acj},#{Bcj},#{Ccj},#{Zcj},#{grade.cId})")
    int insertStudent(Student st);


}
