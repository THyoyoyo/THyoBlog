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
        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        tyUserInfoQueryWrapper.isNotNull("token");
        List<TyUserInfo> list = tyUserInfoMapper.selectList(tyUserInfoQueryWrapper);
        if(list.size()>0){
            for (TyUserInfo tyUserInfo : list) {
                if (tyUserInfo.getState().equals(0)){
                    System.out.println("当前账号已关闭自动启停:-"+tyUserInfo.getUserId());
                    continue;
                }
                //查询用户状态
                Map<String, Object> userInfo = (Map<String, Object>) expressToolsService.getTyUserInfoByToken(tyUserInfo.getToken());
                Map<String, Object> data = (Map<String, Object>) userInfo.get("data");

                Integer code =(Integer) userInfo.get("code");

                if(code.equals(200)){
                    StopTyTime stopTyTime = new StopTyTime();
                    stopTyTime.setStatus(1);
                    stopTyTime.setUserId(data.get("user_id").toString());
                    if(data.get("stop_pc_time").toString().equals("0")){
                        expressToolsService.stopTyTime(stopTyTime);
                        System.out.println("成功暂停:-"+tyUserInfo.getUserId());
                    }
                }else {
                    tyUserInfoMapper.deleteById(tyUserInfo.getId());
                    tyUserInfo.setState(0);
                    tyUserInfoMapper.updateById(tyUserInfo);
                    System.out.println("已失效:-"+tyUserInfo.getUserId());
                }

            }
        }
    }

}
