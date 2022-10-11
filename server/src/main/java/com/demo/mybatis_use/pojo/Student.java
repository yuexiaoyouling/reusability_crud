package com.demo.mybatis_use.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @JsonProperty("sId")
    @TableId(value = "s_id",type = IdType.AUTO)
    private Integer sId;
    @JsonProperty("sClass")
    @TableField("s_class")
    private Integer sClass;
    @JsonProperty("sName")
    @TableField("s_name")
    private String sName;
    @TableField("s_logo")
    private String logo;
    @JsonProperty("Acj")
    @TableField("s_Acj")
    private Integer Acj;
    @JsonProperty("Bcj")
    @TableField("s_Bcj")
    private Integer Bcj;
    @JsonProperty("Ccj")
    @TableField("s_Ccj")
    private Integer Ccj;
    @JsonProperty("Zcj")
    @TableField("s_Zcj")
    private Integer Zcj;
    @TableField(exist = false)
    private MyClass grade;
}
