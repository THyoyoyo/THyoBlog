package com.blogs.task;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.TyUserInfo.TyUserInfoMapper;
import com.blogs.mapper.speed.SpeedInfoMapper;
import com.blogs.model.TyUserInfo.TyUserInfo;
import com.blogs.model.expressTools.StopTyTime;
import com.blogs.model.speed.SpeedInfo;
import com.blogs.service.ExpressToolsService;
import com.blogs.service.SpeedToolService;
import com.blogs.service.TyGameService;
import com.blogs.vo.common.R;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Component
@EnableAsync
public class ScheduledService {

     @Autowired
     ExpressToolsService expressToolsService;

     @Autowired
     TyGameService tyGameService;

     @Autowired
     TyUserInfoMapper tyUserInfoMapper;

     @Autowired
    SpeedInfoMapper speedInfoMapper;

     @Autowired
     SpeedToolService speedToolService;


    @Autowired
    private OkHttpClient okHttpClient;


    /**
     * 腾游加速器监控初始化
     * */
    @Scheduled(cron = "0 0/1  * * * ?")
    @Async
    public void tyTimeCheck() {
        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        tyUserInfoQueryWrapper.isNotNull("user_id");
        List<TyUserInfo> tyUserInfos = tyUserInfoMapper.selectList(tyUserInfoQueryWrapper);
        for (TyUserInfo tyUserInfo : tyUserInfos) {
            //自动暂停时间
            if(tyUserInfo.getState().equals(1)){
                //当前用户错误次数
                Integer errNum = tyUserInfo.getErrNum();
                //如果暂停失败次数大于3次则取消加速
//                if(errNum.compareTo(3) == 1){
//                    tyUserInfo.setState(0);
//                }
                StopTyTime stopTyTime = new StopTyTime();
                stopTyTime.setUserId(tyUserInfo.getUserId());
                stopTyTime.setStatus(1);
                Map<String, Object> stringObjectMap = tyGameService.stopTyTime(stopTyTime);
                System.out.println(stringObjectMap);
                //暂停接口返回code
                Integer code = Integer.valueOf(stringObjectMap.get("code").toString());
                if(code.compareTo(200) !=0){
                    tyUserInfo.setErrNum(++errNum);
                }
                tyUserInfoMapper.updateById(tyUserInfo);
            }
        }
    }




    @Scheduled(cron = "0 0 0 * * ?",zone = "Asia/Shanghai")
    @Async
    public void speedAutoKeyBox(){


        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("state",1);
        List<SpeedInfo> speedInfos = speedInfoMapper.selectList(speedInfoQueryWrapper);


        int THREAD_POOL_SIZE = speedInfos.size();

        // 创建一个任务列表
        List<Runnable> tasks = new ArrayList<>();

        for (SpeedInfo speedInfo : speedInfos) {
            tasks.add(() -> {
                try {
                    speedToolService.asyncOpenBoxByKey(speedInfo,speedInfo.getOpenNum(),speedInfo.getKeyId1(),speedInfo.getKeyNum1(),speedInfo.getBoxId());
                    speedInfo.setUpTime(new Date());
                    speedInfoMapper.updateById(speedInfo);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }


        // 创建线程池并提交所有任务
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        CountDownLatch latch = new CountDownLatch(THREAD_POOL_SIZE);
        for (Runnable task : tasks) {
            executor.submit(() -> {
                try {
                    task.run();
                } finally {
                    latch.countDown();
                }
            });
        }

        // 等待所有任务完成
        try {
            latch.await();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("线程ERR", e);
        } finally {
            executor.shutdown();
        }

    }

}
