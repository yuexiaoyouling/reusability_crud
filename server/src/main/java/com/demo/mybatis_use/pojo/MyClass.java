package com.demo.mybatis_use.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("class")
public class MyClass {
    @JsonProperty("cId")
    @TableId("c_id")
    private int cId;
    @JsonProperty("cName")
    @TableField("c_name")
    private String cName;
    @JsonProperty("students")
    @TableField(exist = false)
    private List<Student> students;
}
