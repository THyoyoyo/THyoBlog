package com.blogs.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * 权限检验
     * */
    @ExceptionHandler(authorityException.class)
    @ResponseBody
    public Map<String, Object> authorityHandler() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "500");
        map.put("message", "你没有权限访问该接口");
        return map;
    }
}
