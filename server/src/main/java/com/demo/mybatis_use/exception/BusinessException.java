package com.demo.mybatis_use.exception;

import com.demo.mybatis_use.pojo.ErrorCodeEnum;

public class BusinessException extends RuntimeException {
    private ErrorCodeEnum errorCodeEnum;

    public BusinessException() {
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.toString());
        this.errorCodeEnum = errorCodeEnum;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }
}
