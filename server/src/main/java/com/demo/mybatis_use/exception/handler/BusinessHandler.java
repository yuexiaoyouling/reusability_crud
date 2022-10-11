package com.demo.mybatis_use.exception.handler;

import com.demo.mybatis_use.exception.BusinessException;
import com.demo.mybatis_use.pojo.ErrorCodeEnum;
import com.demo.mybatis_use.pojo.Msg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Msg handlerBusinessException(BusinessException e) {
        return new Msg(e.getErrorCodeEnum());
    }


    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Msg handlerError() {
        return new Msg(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
    }

}