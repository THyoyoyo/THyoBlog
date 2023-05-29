package com.blogs.controller;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.annotation.Token;
import com.blogs.mapper.speed.SpeedBoxLogMapper;
import com.blogs.mapper.speed.SpeedInfoMapper;
import com.blogs.mapper.user.UserMapper;
import com.blogs.model.speed.SpeedBoxLog;
import com.blogs.model.speed.SpeedInfo;
import com.blogs.model.user.User;
import com.blogs.service.ExpressToolsService;
import com.blogs.service.SpeedToolService;
import com.blogs.service.UserService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.R;
import com.blogs.vo.speed.SavaInfoDto;
import com.blogs.vo.speed.SetAutoBoxInfoVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    SpeedBoxLogMapper speedBoxLogMapper;

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
        speedInfo.setState(0);
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



    @ApiOperation(value = "批量开启宝箱")
    @GetMapping("/openBoxByKeyV2")
    @Token(loginCode = 402)
    public R openBoxByKeyV2(  @RequestParam("boxId")  Integer  boxId,
                              @RequestParam(value = "openNum",defaultValue = "1")  Integer  openNum,
                              @RequestParam("keyNum1") Integer keyNum1,
                              @RequestParam("keyId1")   Integer keyId1) throws Exception {

        Integer userId = CurrentUserUtil.getUserId();

        SpeedInfo speedInfo = speedToolService.SpeedInfoByUserId(userId);

        List<Map<String, Object>> maps = speedToolService.asyncOpenBoxByKey(speedInfo, openNum, keyId1, keyNum1, boxId);

        return R.succeed(maps);
    }


    @ApiOperation(value = "启动关闭")
    @GetMapping("upstate")
    @Token(loginCode = 402)
    public R upstate() throws Exception {
        Integer userId = CurrentUserUtil.getUserId();
        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",userId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);
        if (speedInfo.getState().equals(1)){
            speedInfo.setState(0);
        }else{
            speedInfo.setState(1);
        }
        speedInfoMapper.updateById(speedInfo);
        return R.succeed();
    }

    @ApiOperation(value = "设置自动开启宝箱")
    @PostMapping("setAutoBoxInfo")
    @Token(loginCode = 402)
    public R setAutoBoxInfo(@RequestBody SetAutoBoxInfoVo dto) throws Exception {
        Integer userId = CurrentUserUtil.getUserId();
        QueryWrapper<SpeedInfo> speedInfoQueryWrapper = new QueryWrapper<>();
        speedInfoQueryWrapper.eq("speed_user_id",userId);
        SpeedInfo speedInfo = speedInfoMapper.selectOne(speedInfoQueryWrapper);

        speedInfo.setBoxId(dto.getBoxId());
        speedInfo.setOpenNum(dto.getOpenNum());
        speedInfo.setKeyId1(dto.getKeyId1());
        speedInfo.setKeyNum1(dto.getKeyNum1());
        speedInfoMapper.updateById(speedInfo);

        return R.succeed();
    }


    @ApiOperation(value = "奖励一键领取（每日、签到次数、周末福利）")
    @GetMapping("awardReceiving")
    @Token(loginCode = 402)
    public R awardReceiving() throws Exception {
        List<String> giftid = speedToolService.getGiftid(null);
        ArrayList<Object> objects = new ArrayList<>();
        Object o = null;
        for (int i = 0; i < giftid.size(); i++) {
            if (i == 0) {
                o = speedToolService.dailyCheckIn(0, giftid.get(i),null);
            } else {
                o = speedToolService.dailyCheckIn(1, giftid.get(i),null);
            }
            objects.add(o);

            try {
                Thread.sleep(2000); // 延迟 1 秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return R.succeed(objects);
    }


    @ApiOperation(value = "查询三天内开箱记录")
    @GetMapping("getOpenBoxLog")
    public R getOpenBoxLog() throws Exception {
        Integer userId = CurrentUserUtil.getUserId();

        SpeedInfo speedInfo = speedInfoMapper.selectOne(new QueryWrapper<SpeedInfo>().eq("speed_user_id", userId));
        QueryWrapper<SpeedBoxLog> speedBoxLogQueryWrapper = new QueryWrapper<>();
        speedBoxLogQueryWrapper.eq("speed_id",speedInfo.getId()).ge("created", DateUtil.offsetDay(new Date(), -3)); // 只查询三天内的数据;
        speedBoxLogQueryWrapper.orderByDesc("created");

        List<SpeedBoxLog> speedBoxLogs = speedBoxLogMapper.selectList(speedBoxLogQueryWrapper);

        for (SpeedBoxLog speedBoxLog : speedBoxLogs) {
            speedBoxLog.setDataList((List<Map<String,Object>>) JSON.parse(speedBoxLog.getJson()));
            speedBoxLog.setJson(null);
        }
        return R.succeed(speedBoxLogs);
    }
}
