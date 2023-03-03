package com.blogs.service.imlp;


import com.alibaba.fastjson.JSON;
import com.blogs.model.expressTools.StopTyTime;
import com.blogs.service.TyGameService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TyGameServiceImpl implements TyGameService {

    @Override
    public Map<String, Object> stopTyTime(StopTyTime stopTyTime) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api-admin-js.tengyoujiasu.com/api/v1/user/client/vip_time";
        String userId = stopTyTime.getUserId(); // 替换成实际的user_id值
        Integer status = stopTyTime.getStatus(); // 替换成实际的status值

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("user_id", userId);
        requestBody.put("status", status);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        String response = restTemplate.postForObject(url, requestEntity, String.class);
        Map<String, Object> parse = (Map<String, Object>) JSON.parse(response);
        return parse;
    }

}
