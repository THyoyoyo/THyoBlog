package com.blogs.controller;


import com.alibaba.fastjson.JSON;
import com.blogs.service.SpeedToolService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Api(tags = "QQ飞车工具箱")
@RequestMapping("/api/speedTool")
public class SpeedApiController {

    @Autowired
    SpeedToolService speedToolService;

    @ApiOperation(value = "获取背包（宝箱）信息")
    @GetMapping("/getUserBoxItemInfoV2")
    public R getUserBoxItemInfoV2() throws IOException {
        // referer
        Response response = speedToolService.getUserBoxItemInfoV2();
        return R.succeed(JSON.parse( response.body().string()));
    }


    @ApiOperation(value = "获取背包（赛车，宠物，套餐）信息")
    @GetMapping("/getUserBagInfo")
    public R getUserBagInfo() throws IOException {
        // referer
        Response response = speedToolService.getUserBagInfo();
        return R.succeed(JSON.parse(response.body().string()));
    }

    @ApiOperation(value = "开启宝箱（需要钥匙）")
    @GetMapping("/openBoxByKey")
    public R openBoxByKey() throws IOException {
        // referer  keyId1 keyNum1 boxId
        Response response = speedToolService.openBoxByKey();
        return R.succeed(JSON.parse(response.body().string()));
    }



    @ApiOperation(value = "开启宝箱")
    @GetMapping("/openBox")
    public R openBox() throws IOException {
        //referer boxId
        Response response = speedToolService.openBox();
        return R.succeed(JSON.parse(response.body().string()));
    }
}
