package com.blogs.task;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.TyUserInfo.TyUserInfoMapper;
import com.blogs.model.TyUserInfo.TyUserInfo;
import com.blogs.model.expressTools.StopTyTime;
import com.blogs.service.ExpressToolsService;
import com.blogs.service.TyGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ScheduledService {

     @Autowired
     ExpressToolsService expressToolsService;

     @Autowired
     TyGameService tyGameService;

     @Autowired
     TyUserInfoMapper tyUserInfoMapper;

    /**
     * 腾游加速器监控初始化
     * */
    @Scheduled(cron = "0/50 * * * * ?")
    private void tyTimeCheck() {
        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        tyUserInfoQueryWrapper.isNotNull("user_id");
        List<TyUserInfo> tyUserInfos = tyUserInfoMapper.selectList(tyUserInfoQueryWrapper);
        for (TyUserInfo tyUserInfo : tyUserInfos) {
            //自动暂停时间
            if(tyUserInfo.getState().equals(1)){
                Integer errNum = tyUserInfo.getErrNum();
                if(errNum.compareTo(5) > 0){
                    tyUserInfo.setState(0);
                }

                StopTyTime stopTyTime = new StopTyTime();
                stopTyTime.setUserId(tyUserInfo.getUserId());
                stopTyTime.setStatus(1);
                Map<String, Object> stringObjectMap = tyGameService.stopTyTime(stopTyTime);

                Integer code = Integer.valueOf(stringObjectMap.get("code").toString());
                if(code != 200){
                    tyUserInfo.setErrNum(errNum+1);
                }
                tyUserInfoMapper.updateById(tyUserInfo);
            }
        }
    }
}
