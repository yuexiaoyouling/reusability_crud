package com.demo.mybatis_use.service.impl;


import com.demo.mybatis_use.mapper.MyClassMapper;
import com.demo.mybatis_use.pojo.MyClass;
import com.demo.mybatis_use.service.MyClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyClassServiceImpl implements MyClassService {
    @Resource
    private MyClassMapper myClassMapper;
    @Override
    public List<MyClass> selectAllClass() {

        return myClassMapper.selectList(null);
    }
}
