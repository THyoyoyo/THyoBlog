package com.blogs.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.other.OtherWyyMapper;
import com.blogs.mapper.test.TestMapper;
import com.blogs.model.expressTools.OkHttpMethod;
import com.blogs.model.other.OtherWyy;
import com.blogs.model.test.Test;
import com.blogs.service.ExpressToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
@EnableAsync
public class CommScheduledService {



    @Autowired
    ExpressToolsService expressToolsService;


    @Autowired
    OtherWyyMapper otherWyyMapper;



    @Autowired
    TestMapper testMapper;
}
