package com.blogs.controller;


import com.blogs.model.expressTools.StopTyTime;
import com.blogs.service.TyGameService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(tags="腾游监控模块")
@RequestMapping("/api/tyGame")
public class TyGameController {

    @Autowired
    TyGameService tyGameService;

    @ApiOperation(value = "腾游加速器时间暂停or开启")
    @PostMapping("/stopTyTime")
    public R stopTyTime(@RequestBody StopTyTime stopTyTime){
        try {
            Map<String, Object> stringObjectMap = tyGameService.stopTyTime(stopTyTime);
            return  R.succeed(stringObjectMap);
        } catch (Exception e){
            return R.failed(404,"操作失败,请检查参数是否正确");
        }
    }
}
