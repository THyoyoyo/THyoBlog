package com.blogs.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blogs.annotation.AnnotationTest;
import com.blogs.annotation.Token;
import com.blogs.model.test.Test;
import com.blogs.service.TestService;
import com.blogs.vo.common.R;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/test")
@Api(tags = "测试接口")

public class TestCotroller {
    @Autowired
    TestService testService;

    @Autowired
    private OkHttpClient okHttpClient;


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


    @ApiOperation(value = "并发请求测试")
    @GetMapping("/sendDataTest")
    public R sendDataTest(){

        List<CompletableFuture<Map<String, Object>>> futures = new ArrayList<>();


        for (int i = 0; i < 50; i++) {
            futures.add(fetchUrlAsync("https://www.thyo.xyz//api/expressTools/getQinfo?qq=522307026"));
        }
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()])
        );
        allFutures.join();
        List<Map<String, Object>> responses = futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        return R.succeed(responses);
    }


    private CompletableFuture<Map<String, Object>> fetchUrlAsync(String url) {
        CompletableFuture<Map<String, Object>> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(url)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.readValue(responseBody, new TypeReference<Map<String, Object>>() {});
                future.complete(map);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

}
