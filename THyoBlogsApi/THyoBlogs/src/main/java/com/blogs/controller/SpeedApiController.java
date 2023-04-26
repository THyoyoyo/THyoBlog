package com.blogs.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.annotation.Token;
import com.blogs.mapper.speed.SpeedInfoMapper;
import com.blogs.mapper.user.UserMapper;
import com.blogs.model.speed.SpeedInfo;
import com.blogs.model.user.User;
import com.blogs.service.ExpressToolsService;
import com.blogs.service.SpeedToolService;
import com.blogs.service.UserService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.R;
import com.blogs.vo.speed.SavaInfoDto;
import com.blogs.vo.speed.SpeedLoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RestController
@Api(tags = "QQ飞车工具箱")
@RequestMapping("/api/speedTool")
@Slf4j
public class SpeedApiController {

    @Autowired
    SpeedToolService speedToolService;

    @Autowired
    ExpressToolsService expressToolsService;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    SpeedInfoMapper speedInfoMapper;

    @ApiOperation(value = "获取背包（宝箱）信息")
    @GetMapping("/getUserBoxItemInfoV2")
    @Token(loginCode = 402)
    public R getUserBoxItemInfoV2() throws Exception {
        // referer
        Response response = speedToolService.getUserBoxItemInfoV2();
        return R.succeed(JSON.parse( response.body().string()));
    }


    @ApiOperation(value = "获取背包（赛车，宠物，套餐）信息")
    @GetMapping("/getUserBagInfo")
    @Token(loginCode = 402)
    public R getUserBagInfo() throws Exception {
        // referer
        Response response = speedToolService.getUserBagInfo();
        return R.succeed(JSON.parse(response.body().string()));
    }

    @ApiOperation(value = "开启宝箱（需要钥匙）")
    @GetMapping("/openBoxByKey")
    @Token(loginCode = 402)
    public R openBoxByKey(
            @RequestParam("boxId")  Integer  boxId,
            @RequestParam("keyNum1") Integer keyNum1,
            @RequestParam("keyId1")   Integer keyId1
    ) throws Exception {
        // referer  keyId1 keyNum1 boxId
        Response response = speedToolService.openBoxByKey(keyId1,keyNum1,boxId);
        return R.succeed(JSON.parse(response.body().string()));
    }



    @ApiOperation(value = "开启宝箱")
    @GetMapping("/openBox")
    @Token(loginCode = 402)
    public R openBox(@RequestParam("boxId") Integer boxId) throws Exception {
        //referer boxId
        Response response = speedToolService.openBox(boxId);
        return R.succeed(JSON.parse(response.body().string()));
    }



    @ApiOperation("QQ飞车工具创建用户")
    @PostMapping("/savaInfo")
    public R savaInfo(@RequestBody SavaInfoDto info){


        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account", info.getAccount());

        User user1 = userMapper.selectOne(userQueryWrapper);
        if(user1 != null){
            return R.failed(404,"账户："+info.getAccount()+"已存在");
        }


        User user = new User();
        user.setAccount(info.getAccount());
        user.setPassword(info.getPassword());
        Map<String, Object> qqInfo = expressToolsService.getQqInfo(info.getQq());
        try {
            user.setHead(qqInfo.get("qlogo").toString());
            user.setName(qqInfo.get("name").toString());
        }catch(Exception e){
            return R.failed(404,"QQ号查询失败");
        }

        user.setState("1");
        user.setRoleId(9);
        int userId = userService.addUser(user);
        SpeedInfo speedInfo = new SpeedInfo();
        BeanUtils.copyProperties(info,speedInfo);
        speedInfo.setSpeedUserId(userId);
        speedInfoMapper.insert(speedInfo);

        return R.succeed();
    }

    @ApiOperation("编辑referer链接")
    @PostMapping("/upReferer")
    @Token(loginCode = 402)
    public R upReferer(@RequestBody SpeedInfo dto) throws Exception {

        Integer userId = CurrentUserUtil.getUserId();

        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",userId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);

        BeanUtils.copyProperties(dto,speedInfo,"id");

        speedInfoMapper.updateById(speedInfo);

        return  R.succeed();
    }



    @ApiOperation("获取用户Referer数据")
    @GetMapping("/getRefererInfo")
    @Token(loginCode = 402)
    public R getRefererInfo() throws Exception {

        Integer userId = CurrentUserUtil.getUserId();
        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",userId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);

        return R.succeed(speedInfo);
    }



    @ApiOperation("设置定时任务")
    @PostMapping("/setTimingTask")
    public R setTimingTask(){
        return R.succeed();
    }

}
