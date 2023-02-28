package com.blogs.task;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.TyUserInfo.TyUserInfoMapper;
import com.blogs.model.TyUserInfo.TyUserInfo;
import com.blogs.model.expressTools.StopTyTime;
import com.blogs.service.ExpressToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScheduledService {

     @Autowired
     ExpressToolsService expressToolsService;

     @Autowired
     TyUserInfoMapper tyUserInfoMapper;

    /**
     * 腾讯加速器监控
     * */
    @Scheduled(cron = "0/5 * * * * ?")
    private void tyTimeChange() {
     
    }

}
