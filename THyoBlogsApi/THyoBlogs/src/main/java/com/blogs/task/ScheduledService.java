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
    @Scheduled(cron = "0 0/1  * * * ?")
    private void tyTimeCheck() {
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



}
