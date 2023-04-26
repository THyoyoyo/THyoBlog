package com.blogs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.constants.JwtConstants;
import com.blogs.mapper.user.UserMapper;
import com.blogs.model.user.User;
import com.blogs.service.ExpressToolsService;
import com.blogs.service.SystemService;
import com.blogs.service.UserService;
import com.blogs.util.CookieUtil;
import com.blogs.util.CurrentUserUtil;
import com.blogs.util.IPUtil;
import com.blogs.util.JWTUtil;
import com.blogs.vo.common.R;
import com.blogs.vo.login.RegisterVo;
import com.blogs.vo.login.ReturnUserLoginInfoVo;
import com.blogs.vo.user.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
@Api(tags = "登录注册模块")
public class LoginController {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    SystemService systemService;

    @Autowired
    ExpressToolsService expressToolsService;




    @PostMapping("/register")
    @ApiOperation("用户登录")
    public R Login(@RequestBody RegisterVo registerVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ip = IPUtil.getIpAddr(request);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account",registerVo.getAccount()).eq("password",registerVo.getPassword());
        User user = userMapper.selectOne(userQueryWrapper);
        if(user == null){
            return R.failed(402,"帐号或密码错误");
        }
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(user,userInfoVo);
        String token = JWTUtil.generateToken(user.getId(), ip);
        userInfoVo.setToken(token);
        userInfoVo.setLastTime(new Date());

        //更新权限信息
        userInfoVo.setMenus(systemService.getRoleMenus(user.getRoleId()));

        //客户端添加Cookie

        CookieUtil.set(response, JwtConstants.COOKIE_TOKEN, token, JwtConstants.TOKEN_EXPIRE_TIME.intValue());



        //更新用户信息
        user.setToken(token);
        user.setLastTime(new Date());

        userMapper.updateById(user);


        return  R.succeed(userInfoVo);
    }


    @GetMapping("/out")
    @ApiOperation("用户退出")
    public R out (HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer userId = CurrentUserUtil.getUserId();
        if(userId !=null){
            User user = new User();
            user.setId(userId);
            user.setToken("0");
            userMapper.updateById(user);
        }
        CookieUtil.set(response, JwtConstants.COOKIE_TOKEN, null,1);
        return  R.succeed(userId);
    }

    @GetMapping("/userLoginFromQq")
    @ApiOperation("通过QQ登录")
    public R userLoginFromQq(@RequestParam("qq") String qq,@RequestParam("time") Long time){
        long atTime = new Date().getTime();
         if(atTime - time >1350){
             String str = "错误,请重新尝试登录" +(atTime - time);
             return  R.failed(404,str);
         }

        Map<String,Object> qqInfo = expressToolsService.getQqInfo(qq);
        if(qqInfo.get("status").equals("error")){
            return R.failed(404,"未获取到QQ任何信息,请重新输入");
        }

        //查找用户是否已存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("account","QQ"+qq).last("LIMIT 0,1");
        User user = userMapper.selectOne(userQueryWrapper);

        if (user == null){
            //新建
            user = new User();
            user.setAccount("QQ"+qq);
            user.setHead(qqInfo.get("qlogo").toString());
            user.setName(qqInfo.get("name").toString());
            user.setPassword("123456");
            user.setState("1");
            user.setRoleId(8);
            user.setCreationTime(new Date());
            userMapper.insert(user);
        }else {
            //只有游客角色 才更新
            if (user.getRoleId().equals(8)){
                user.setHead(qqInfo.get("qlogo").toString());
                user.setName(qqInfo.get("name").toString());
            }
        }
        ReturnUserLoginInfoVo returnUserLoginInfoVo = new ReturnUserLoginInfoVo();
        BeanUtils.copyProperties(user,returnUserLoginInfoVo);
        return R.succeed(returnUserLoginInfoVo);
    }
}
