package com.blogs.controller;

import com.alibaba.fastjson.JSON;
import com.blogs.service.LolToolsService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@Api(tags = "掌上联盟工具箱")
@RequestMapping("/api/LolTool")
public class LolToolsController {

      @Autowired
      LolToolsService lolToolsService;


    @GetMapping("/getBattleList")
    @ApiOperation(value = "获取最近对局信息（普通模式）")
    public R getBattleList() throws IOException {
        Response battleList = lolToolsService.getBattleList();
        return R.succeed(JSON.parse(battleList.body().string()));
    }


    @GetMapping("/getOnlineStatus")
    @ApiOperation(value = "在线状态")
    public R getOnlineStatus() throws IOException {
        Response onlineStatus = lolToolsService.getOnlineStatus();
        return R.succeed(JSON.parse(onlineStatus.body().string()));
    }


    @GetMapping("/userProfileInfo")
    @ApiOperation(value = "个人信息")
    public R userProfileInfo() throws IOException {
        Response response = lolToolsService.userProfileInfo();

        return R.succeed(JSON.parse(response.body().string()));
    }



    @GetMapping("/getExploitBattleList")
    @ApiOperation(value = "获取最近对局信息(云顶之弈)")
    public R getExploitBattleList() throws IOException {
        Response exploitBattleList = lolToolsService.getExploitBattleList();
        return R.succeed(JSON.parse(exploitBattleList.body().string()));
    }

}
