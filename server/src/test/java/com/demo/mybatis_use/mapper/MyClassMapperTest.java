package com.demo.mybatis_use.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@MapperScan("com.demo.mybatis_use.mapper")
class MyClassMapperTest {

    @Resource
    private MyClassMapper classMapper;
    @Test
    void testSelect(){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("c_id", 1);
        List list = classMapper.selectList(queryWrapper);
        System.out.println(list);

    }
    @Test
    void testSelectClassById(){
        System.out.println(classMapper.selectClassById(1));
    }
    @Test
    void testSelectClassWithAllStudents(){
        System.out.println(classMapper.selectClassWithAllStudents(1));
    }

}