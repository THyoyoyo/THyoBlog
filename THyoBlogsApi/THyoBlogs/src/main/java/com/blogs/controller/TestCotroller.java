package com.blogs.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blogs.annotation.AnnotationTest;
import com.blogs.annotation.Token;
import com.blogs.model.test.Test;
import com.blogs.service.TestService;
import com.blogs.util.IPUtil;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import sun.plugin.com.JavaClass;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@Api(tags = "测试接口")

public class TestCotroller {
    @Autowired
    TestService testService;


    @ApiOperation(value = "根据ID查询用户")
    @GetMapping("/user")
    public R getUserId(@RequestParam("id") Integer id) {
        Test test = testService.getUserId(id);
        return R.succeed(test);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/addUser")
    @Token
    public R addUser(@RequestBody Test user) {
        testService.adduser(user);
        return R.succeed();
    }


    @ApiOperation(value = "获取Ip")
    @GetMapping("/getIp")
    public R getIp(HttpServletRequest request) {
        String ipStr = request.getHeader("x-forwarded-for");
        if (StringUtils.isBlank(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
            ipStr = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ipStr) || "unknown".equalsIgnoreCase(ipStr)) {
            ipStr = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ipStr.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ipStr = str;
                break;
            }
        }
        //目的是将localhost访问对应的ip 0:0:0:0:0:0:0:1 转成 127.0.0.1。
        String s = ipStr.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ipStr;

        return R.succeed(s);
    }


    @ApiOperation(value = "权限检验/拦截-测试")
    @GetMapping("/annotationTest")
    @AnnotationTest(name = "test")
    public R annotationTest() {
        return R.succeed();
    }

    @ApiOperation(value = "商品秒杀Demo")
    @PostMapping("/instantCommodity")
    public R instantCommodity() {
        Boolean stau = testService.instantCommodity();

        if(stau){
            return R.succeed(200,"秒杀成功");
        }

        return R.failed(401,"秒杀失败");
    }

}
