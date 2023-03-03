package com.blogs.controller;


import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.TyUserInfo.TyUserInfoMapper;
import com.blogs.model.TyUserInfo.TyUserInfo;
import com.blogs.model.TyUserInfo.TyUserList;
import com.blogs.model.TyUserInfo.TyUserSava;
import com.blogs.model.expressTools.OkHttpMethod;
import com.blogs.model.expressTools.StopTyTime;
import com.blogs.service.ExpressToolsService;
import com.blogs.service.TyGameService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Api(tags = "腾游监控模块")
@RequestMapping("/api/tyGame")
public class TyGameController {

    @Autowired
    TyGameService tyGameService;

    @Autowired
    TyUserInfoMapper tyUserInfoMapper;


    @Autowired
    ExpressToolsService expressToolsService;

    @ApiOperation(value = "腾游加速器时间暂停or开启")
    @PostMapping("/stopTyTimePassWord")
    public R stopTyTime(@RequestBody StopTyTime stopTyTime) {
        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        tyUserInfoQueryWrapper.eq("qq", stopTyTime.getQq()).eq("password", stopTyTime.getPassword()).last("LIMIT 0,1");
        TyUserInfo tyUserInfo = tyUserInfoMapper.selectOne(tyUserInfoQueryWrapper);
        if (tyUserInfo == null) {
            return R.failed(404, "密码错误");
        }
        try {
            stopTyTime.setUserId(tyUserInfo.getUserId());
            Map<String, Object> stringObjectMap = tyGameService.stopTyTime(stopTyTime);
            return R.succeed(stringObjectMap);
        } catch (Exception e) {
            return R.failed(404, "操作失败,请检查参数是否正确");
        }
    }

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/userList")
    public R userList(@RequestParam(value = "userId", required = false) String id) {
        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        if (id != null) {
            tyUserInfoQueryWrapper.eq("user_id", id);
        }

        List<TyUserInfo> tyUserInfos = tyUserInfoMapper.selectList(tyUserInfoQueryWrapper);

        List<TyUserList> tyUserLists = new ArrayList<>();

        for (TyUserInfo tyUserInfo : tyUserInfos) {
            Map<String, Object> qqInfo = (Map<String, Object>) expressToolsService.getQqInfo(tyUserInfo.getQq());
            TyUserList tyUserList = new TyUserList();
            tyUserList.setQqName(qqInfo.get("nickname").toString());
            tyUserList.setQqImg(qqInfo.get("headimg").toString());
            String s = tyUserInfo.getUserId().replaceAll("(?<=\\d{3})\\d(?=\\d{5})", "*");
            tyUserInfo.setUserId(s);
            BeanUtils.copyProperties(tyUserInfo, tyUserList);
            tyUserLists.add(tyUserList);
        }
        return R.succeed(tyUserLists);
    }

    @ApiOperation(value = "新增、编辑用户")
    @PostMapping("/userSava")
    public R userSava(@RequestBody TyUserSava dto) {

        if(dto.getId() == null ){
            QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
            tyUserInfoQueryWrapper.eq("qq", dto.getQq());
            Integer userNum = tyUserInfoMapper.selectCount(tyUserInfoQueryWrapper);
            if(userNum>0){
                return  R.failed(402,"对不起，QQ号只能存在一个");
            }

            TyUserInfo tyUserInfo = new TyUserInfo();
            BeanUtils.copyProperties(dto, tyUserInfo);
            tyUserInfo.setState(0);
            tyUserInfo.setCreated(new Date());
            tyUserInfoMapper.insert(tyUserInfo);
        }else {
            QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
            tyUserInfoQueryWrapper.eq("qq", dto.getQq()).eq("password", dto.getCheckPassword()).last("LIMIT 0,1");
            TyUserInfo tyUserInfo = tyUserInfoMapper.selectOne(tyUserInfoQueryWrapper);
            if (tyUserInfo == null) {
                return R.failed(404, "原始密码错误");
            }
            BeanUtils.copyProperties(dto, tyUserInfo);
            tyUserInfo.setState(0);
            tyUserInfoMapper.updateById(tyUserInfo);
        }

        return R.succeed();
    }

    @ApiOperation(value = "启动or关闭监测")
    @GetMapping("/upCheck")
    private R upCheck(@RequestParam("password") String password, @RequestParam("qq") String qq, @RequestParam("state") Integer state) {
        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        tyUserInfoQueryWrapper.eq("qq", qq).eq("password", password).last("LIMIT 0,1");
        TyUserInfo tyUserInfo = tyUserInfoMapper.selectOne(tyUserInfoQueryWrapper);
        if (tyUserInfo == null) {
            return R.failed(404, "密码错误");
        }
        tyUserInfo.setState(state);
        tyUserInfoMapper.updateById(tyUserInfo);
        return R.succeed();
    }


    @ApiOperation(value = "验证密码")
    @GetMapping("/checkUserInfo")
    private R checkUserInfo(@RequestParam("password") String password, @RequestParam("qq") String qq){

        QueryWrapper<TyUserInfo> tyUserInfoQueryWrapper = new QueryWrapper<>();
        tyUserInfoQueryWrapper.eq("qq", qq).eq("password", password).last("LIMIT 0,1");
        TyUserInfo tyUserInfo = tyUserInfoMapper.selectOne(tyUserInfoQueryWrapper);
        if (tyUserInfo == null) {
            return R.failed(404, "密码错误");
        }

        return R.succeed(tyUserInfo);
    }




    private String generateRandomString(){

        String CHARACTERS = "0123456789ABCDEF";
        int LENGTH = 12;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }


    @ApiOperation(value = "发送短信")
    @GetMapping("/upCode")
    private R upCode(@RequestParam("phone") String phone,
                     @RequestParam("ticket") String ticket,
                     @RequestParam("randstr") String randstr
                     ){
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://api-login.tengyoujiasu.com/api/v1/yb_login/gw/login_send");
        okHttpMethod.setMethod("get");
        HashMap<String, String> param = new HashMap<>();
        String randomMac = generateRandomString();
        param.put("phone",phone);
        param.put("ticket",ticket);
        param.put("randstr",randstr);
        param.put("channel_number","PV00001");
        param.put("mac",randomMac);
        param.put("type","gw_phone_login");
        param.put("ct","1");
        param.put("cn","PV00001");
        param.put("af","0");
        param.put("cv","4.0.1.3");
        okHttpMethod.setParam(param);
        Map<String, Object> stringObjectMap = (Map<String, Object>) expressToolsService.okHttpMethod(okHttpMethod);
        stringObjectMap.put("mac",randomMac);
        return R.succeed(stringObjectMap);
    }

    @ApiOperation(value = "通过短信Code进行登录")
    @GetMapping("/codeLogin")
    private R codeLogin(@RequestParam("phone") String phone,
                        @RequestParam("code") String code,
                        @RequestParam(value = "mac" ,required = false) String mac
    ){
        if(mac==null){
            mac = generateRandomString();
        }
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://api-login.tengyoujiasu.com/api/v1/yb_login/gw/login_phone");
        okHttpMethod.setMethod("get");
        HashMap<String, String> param = new HashMap<>();
        param.put("phone",phone);
        param.put("code",code);
        param.put("channel_number","PV00001");
        param.put("mac",mac);
        param.put("type","gw_phone_login");
        param.put("ct","1");
        param.put("cn","PV00001");
        param.put("af","0");
        param.put("cv","4.0.1.3");
        okHttpMethod.setParam(param);
        Map<String, Object> map = (Map<String, Object>) expressToolsService.okHttpMethod(okHttpMethod);
        return R.succeed(map);
    }


    @ApiOperation(value = "获取微信二维码登录")
    @GetMapping("/getQrImg")
    private R getQrImg(){
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://api-login.tengyoujiasu.com/api/v1/yb_login/gzh/ticket");
        okHttpMethod.setMethod("post");
        HashMap<String, String> param = new HashMap<>();
        param.put("channel_number","PV00001");
        param.put("ct","0");
        okHttpMethod.setParam(param);
        Map<String, Object> map = (Map<String, Object>) expressToolsService.okHttpMethod(okHttpMethod);
        return R.succeed(map);
    }


    @ApiOperation(value = "查询二维码是否登录")
    @GetMapping("/qrTicketUser")
    private R qrTicketUser(@RequestParam("ticket") String ticket ){
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://api-login.tengyoujiasu.com/api/v1/yb_login/gzh/ticket_user");
        okHttpMethod.setMethod("post");
        HashMap<String, String> param = new HashMap<>();
        param.put("ticket",ticket);
        HashMap<String, String> header = new HashMap<>();
        header.put("Referer","https://www.tengyoujiasu.com/");
        header.put("Origin","https://www.tengyoujiasu.com");
        header.put("Host","api-login.tengyoujiasu.com");
        header.put("content-type","application/json");
        okHttpMethod.setParam(param);
        okHttpMethod.setHeader(header);
        Map<String, Object> map = (Map<String, Object>) expressToolsService.okHttpMethod(okHttpMethod);
       return R.succeed(map);
    }

    @ApiOperation(value = "通过token获取用户信息")
    @GetMapping("/getTyUserInfo")
    private R getTyUserInfo (@RequestParam("token") String token){
        OkHttpMethod okHttpMethod = new OkHttpMethod();
        okHttpMethod.setUrl("https://api-admin-js.tengyoujiasu.com/api/v1/user/gw/userinfo");
        okHttpMethod.setMethod("post");
        HashMap<String, String> header = new HashMap<>();
        header.put("token",token);
        header.put("referer","https://www.tengyoujiasu.com");
        header.put("refeorigin","https://www.tengyoujiasu.comrer");
        header.put("content-type","application/json");
        okHttpMethod.setHeader(header);
        Map<String, Object> map = (Map<String, Object>) expressToolsService.okHttpMethod(okHttpMethod);
        return R.succeed(map);
    };
}
