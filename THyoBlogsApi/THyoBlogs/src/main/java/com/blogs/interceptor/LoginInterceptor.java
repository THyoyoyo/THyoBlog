package com.blogs.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.annotation.Token;
import com.blogs.mapper.user.UserMapper;
import com.blogs.model.user.User;
import com.blogs.service.UserService;
import com.blogs.util.CookieUtil;
import com.blogs.util.JWTUtil;
import com.blogs.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    UserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Token annotation;
        String message = "请先登录!";

        // 如果不是映射到方法直接通过
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Token.class);
        }else{
            return true;
        }

        //没有声明需要权限,或者声明不验证权限
        if(annotation == null || annotation.validate() == false){
            return true;
        }


        String token = CookieUtil.getValue(request, "cookieToken");
        Integer userId;

        try {
            if(token != null){
                userId = JWTUtil.getUserId(token);
                System.out.println(userService);
                UserVo userInfo = userService.getUserInfo(userId);
                //查询不到用户
                if(userInfo == null){
                    message = "userNUll,请登录";
                }
                //  验证token
                else  if (!JWTUtil.verify(token)){
                    message = "登录已经失效,请重新登录2";
                }
                //token不一致
                else if (!userInfo.getToken().equals(token)){
                    message = "登录已经失效,请重新登录1";
                }

                else {
                    return  true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> map = new HashMap<>();
        map.put("code",401);
        map.put("message",message);
        JSONObject jsonObject = new JSONObject(map);
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(jsonObject);
        writer.close();
        response.flushBuffer();
        return false;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
