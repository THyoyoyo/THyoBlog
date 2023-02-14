package com.blogs.interceptor;

import com.blogs.annotation.AnnotationTest;
import com.blogs.exception.authorityException;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            //反射
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            AnnotationTest annotationTest = handlerMethod.getMethod().getAnnotation(AnnotationTest.class);
            if(annotationTest !=null){
                //权限检验逻辑
                      if(annotationTest.name()!="test"){
                          throw new authorityException(500,"你没有该权限");
                      }
            }
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
