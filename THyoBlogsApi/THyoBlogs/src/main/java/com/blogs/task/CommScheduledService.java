package com.blogs.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.other.OtherWyyMapper;
import com.blogs.model.expressTools.OkHttpMethod;
import com.blogs.model.other.OtherWyy;
import com.blogs.service.ExpressToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class CommScheduledService {



    @Autowired
    ExpressToolsService expressToolsService;


    @Autowired
    OtherWyyMapper otherWyyMapper;



    /**
     * 公共爬取
     * */
    @Scheduled(cron = "0/1 * * * * ?")
    private void commTaekGetData(){


    }

}
