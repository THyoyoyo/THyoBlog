package com.blogs.task;

import com.blogs.model.expressTools.OkHttpMethod;
import com.blogs.service.ExpressToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class WyyScheduledService {



    @Autowired
    ExpressToolsService expressToolsService;


    @Scheduled(cron = "0/1 * * * * ?")
    private void getWyyComment(){
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://api.uomg.com/api/comments.163?format=json");
        okHttpMethod.setMethod("get");
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("format","json");
        okHttpMethod.setParam(paramMap);

        Map<String, Object> okMap = (Map<String, Object>) expressToolsService.okHttpMethod(okHttpMethod);

        System.out.println(okMap);

    }

}
