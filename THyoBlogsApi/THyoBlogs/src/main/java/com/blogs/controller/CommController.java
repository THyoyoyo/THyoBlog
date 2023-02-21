package com.blogs.controller;


import com.alibaba.fastjson.JSON;
import com.blogs.annotation.Token;
import com.blogs.model.common.Translate;
import com.blogs.service.CommService;
import com.blogs.util.MD5;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/comm")
@Api(tags = "公共模块")
public class CommController {

    @Autowired
    CommService commService;

    @ApiOperation(value = "获取上传token")
    @GetMapping("/getQlyToken")
    public R getQlyToken(){
        String uploadFileToken = commService.getUploadFileToken();
         return  R.succeed(uploadFileToken);
    }


    @ApiOperation(value = "获取当前系统时间")
    @GetMapping("/getTime")
    public R getTime(){
        return  R.succeed(new Date());
    }


    @ApiOperation(value = "百度翻译")
    @PostMapping("/getTranslate")
    @Token
    public R getTranslate(@RequestBody Translate dto){
        String text = dto.getText();
        //公共值
        String APP_ID = "20221222001504909";
        String KEY = "hsE0gwQfmF3YRGMZ07VB";
        String URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        // 签名
        String src = APP_ID + text + salt + KEY; // 加密前的原文

        //Post-提交
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String>  multiValueMap= new LinkedMultiValueMap<>();
        multiValueMap.add("q", dto.getText());
        multiValueMap.add("from", dto.getFrom());
        multiValueMap.add("to", dto.getTo());
        multiValueMap.add("appid", APP_ID);
        multiValueMap.add("salt", salt);
        multiValueMap.add("sign", MD5.md5(src));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(multiValueMap, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);

        Map<String,Object> parse = (Map<String, Object>) JSON.parse(response.getBody());

        if(parse.get("error_code") == null){
            return R.succeed(parse);
        }else {
            return R.failed(402,"翻译繁忙,请稍后再试");
        }
    }
}
