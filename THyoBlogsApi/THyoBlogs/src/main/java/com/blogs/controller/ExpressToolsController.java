package com.blogs.controller;


import com.alibaba.fastjson.JSON;
import com.blogs.annotation.Token;
import com.blogs.model.expressTools.Botany;
import com.blogs.model.expressTools.Translate;
import com.blogs.util.MD5;
import com.blogs.vo.common.R;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/expressTools")
@Api(tags = "扩展工具")
public class ExpressToolsController {

    @ApiOperation(value = "百度翻译")
    @PostMapping("/getTranslate")
    @Token
    public R getTranslate(@RequestBody Translate dto) {
        String text = dto.getText();
        //公共值
        String APP_ID = "20221222001504909";
        String KEY = "hsE0gwQfmF3YRGMZ07VB";
        String URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        // 签名
        String src = APP_ID + text + salt + KEY; // 加密前的原文
        try {
            //Post-提交
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("q", dto.getText());
            multiValueMap.add("from", dto.getFrom());
            multiValueMap.add("to", dto.getTo());
            multiValueMap.add("appid", APP_ID);

            multiValueMap.add("salt", salt);
            multiValueMap.add("sign", MD5.md5(src));
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(multiValueMap, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(URL, request, String.class);

            Map<String, Object> parse = (Map<String, Object>) JSON.parse(response.getBody());

            if (parse.get("error_code") == null) {
                return R.succeed(parse);
            } else {
                return R.failed(402, "翻译繁忙,请稍后再试");
            }
        } catch (Throwable e) {
            return R.failed(403, "发生错误,请稍后再试");
        }
    }


    @ApiOperation(value = "植物图片识别")
    @PostMapping("/getBotany")
    public R getBotany(@RequestBody Botany dto) {
        String API_Key = "FHSV4NooLfsVTm8sKxjc3Zso";
        String Secret_Key = "zsvQGcqdAMFbWXPH5dX1jGpEf0XoZLRU";
        String AccessTokenUrl = "https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials" + "&client_id=" + API_Key + "&client_secret=" + Secret_Key;

        String imgUrl = dto.getImgUrl();
        String imgBase64 = dto.getImgBase64();
        try {
            //Post-AccessToken
            RestTemplate tokenRestTemplate = new RestTemplate();
            HttpHeaders tokenHeaders = new HttpHeaders();
            tokenHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(null, tokenHeaders);
            ResponseEntity<String> response = tokenRestTemplate.postForEntity(AccessTokenUrl, tokenRequest, String.class);
            Map<String, Object> tokenParse = (Map<String, Object>) JSON.parse(response.getBody());

            //权限token
            String accessToken = tokenParse.get("access_token").toString();
            String plantUrl = "https://aip.baidubce.com/rest/2.0/image-classify/v1/plant?access_token=" + accessToken;

            RestTemplate plantRestTemplate = new RestTemplate();
            HttpHeaders plantHeaders = new HttpHeaders();
            plantHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

            if(dto.getImgUrl() != null){
                multiValueMap.add("url", imgUrl);
            }

            if(multiValueMap.get("url") == null && imgBase64 !=null){
                multiValueMap.add("image", imgBase64);
            }

            if(dto.getBaikeNum() != null ){
                multiValueMap.add("baike_num", "1");
            }

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(multiValueMap, plantHeaders);
            ResponseEntity<String> plantResponse = plantRestTemplate.postForEntity(plantUrl, request, String.class);
            Map<String, Object> parse = (Map<String, Object>) JSON.parse(plantResponse.getBody());
            return R.succeed(parse);
        } catch (Throwable e) {
            return R.failed(403, "发生错误,请稍后再试");
        }
    }
}
