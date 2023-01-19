package com.blogs.controller;


import com.blogs.annotation.Token;
import com.blogs.model.user.User;
import com.blogs.service.UserService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.R;
import com.blogs.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Api(tags = "用户账户相关")
public class UserCotroller {


    @Autowired
    UserService userService;

    @ApiOperation(value = "查看用户信息")
    @GetMapping("/info")
    public R getUserInfo(@RequestParam("id") Integer id) {
        UserVo userInfo = userService.getUserInfo(id);
        return R.succeed(userInfo);
    }


    @ApiOperation(value = "查看用户信息")
    @GetMapping("/checkInfo")
    public R checkInfo() throws Exception {
        Integer userId = CurrentUserUtil.getUserId();
        if(userId!=null){
            UserVo userInfo = userService.getUserInfo(userId);
            return R.succeed();
        }else {
            return R.failed(401,"未检测到登录信息,请登录后在尝试！");
        }

    }

    @ApiOperation(value = "用户列表")
    @GetMapping("/list")
    @Token
    public R getUserList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSzie
    ) {
        return R.succeed(userService.getUsetList(page, pageSzie));
    }

    @ApiOperation(value = "新增用户")
    @PostMapping("/add")
    @Token
    public R addUser(@RequestBody User user) {
          userService.addUser(user);
          return R.succeed();
    }

    @ApiOperation(value = "更新用户")
    @PostMapping("/up")
    @Token
    public R upUser(@RequestBody User user) {
        userService.upUser(user);
        return R.succeed();
    }

    @ApiOperation(value = "删除用户")
    @PostMapping("/del")
    @Token
    public R delUser(@RequestParam("id") Integer id) {
        userService.delUser(id);
        return R.succeed();
    }

}
