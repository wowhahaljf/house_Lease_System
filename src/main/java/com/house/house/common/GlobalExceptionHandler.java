package com.house.house.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {Controller.class,ResponseBody.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public String exceptionHandler (SQLIntegrityConstraintViolationException ex){
        System.out.println("---------------异常处理器------------");
        log.error(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){
            String[]spilt=ex.getMessage().split(" ");
            System.out.println(spilt[2].substring(1,4));
             return "用户名 "+spilt[2].substring(1,spilt[2].length()-1)+"已存在";
        }
        return "未知错误";
    }
}
