package com.demo.mybatis_use.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 封装了错误码和错误描述信息
 *
 * @author OneIce
 * @since 2021/3/22 23:10
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCodeEnum {
    OK("00000", "一切正确"),
    INSERT_ERROR("A0000", "数据添加错误"),
    UPDATE_ERROR("A0001", "数据更新错误"),
    DELETE_ERROR("A0400", "数据删除错误"),
    SELECT_EMPTY("A0410", "数据查询错误"),
    SYSTEM_EXECUTION_ERROR("B0001", "系统执行出错");

    /**
     * 错误码
     */
    private String value;
    /**
     * 错误描述信息
     */
    private String desc;

    ErrorCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + value + "]" + desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}

