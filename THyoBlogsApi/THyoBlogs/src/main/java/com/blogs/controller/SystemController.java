package com.blogs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.annotation.Token;
import com.blogs.mapper.system.SystemImgsMapper;
import com.blogs.mapper.system.WebConfigMapper;
import com.blogs.model.system.*;
import com.blogs.service.SystemService;
import com.blogs.vo.common.R;
import com.blogs.vo.system.SavaUpLoadWebFileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/system")
@Api(tags = "网站设置")
public class SystemController {

    @Autowired
    SystemService systemService;

    @Autowired
    SystemImgsMapper systemImgsMapper;

    @Autowired
    WebConfigMapper webConfigMapper;

    /**
     * 菜单列表
     */
    @ApiOperation("菜单列表")
    @GetMapping("/menus/list")
    @Token
    public R getMenusList(@RequestParam(value = "type", defaultValue = "all") String type) {
        List<SystemMenus> systemMenus = systemService.getSystemMenus(type);
        return R.succeed(systemMenus);
    }

    /**
     * 菜单新增-修改
     */
    @ApiOperation("菜单新增、修改")
    @PostMapping("/menus/sava")
    @Token
    public R menusSava(@RequestBody SystemMenus systemMenus) throws Exception {
        systemService.systemSava(systemMenus);
        return R.succeed();
    }


    /**
     * 菜单删除
     */
    @GetMapping("/menus/del")
    @ApiOperation("菜单删除")
    @Token
    public R getMenusList(@RequestParam("id") Integer id) {
        systemService.systemDel(id);
        return R.succeed();
    }


    /**
     * 角色列表
     */
    @ApiOperation("角色列表")
    @GetMapping("/role/list")
    @Token
    public R getRoleList() {
        List<SystemRoleInfo> systemRole = systemService.getSystemRole();
        return R.succeed(systemRole);
    }

    /**
     * 角色新增-修改
     */
    @ApiOperation("角色新增、修改")
    @PostMapping("/role/sava")
    @Token
    public R roleSava(@RequestBody SystemRoleInfo systemRoleInfo) throws Exception {
        systemService.roleSava(systemRoleInfo);
        return R.succeed();
    }


    /**
     * 角色删除
     */
    @GetMapping("/role/del")
    @ApiOperation("角色删除")
    @Token
    public R getRoleList(@RequestParam("id") Integer id) {
        systemService.roleDel(id);
        return R.succeed();
    }

    /**
     * 获取角色拥有的菜单
     */

    @GetMapping
    @ApiOperation("获取角色拥有的菜单")
    @Token
    public R getRoleMenus(@RequestParam("id") Integer id) throws Exception {
        List<SystemMenus> roleMenus = systemService.getRoleMenus(id);
        return R.succeed(roleMenus);
    }


    /**
     * 保存上传文件资源路径
     */
    @PostMapping("/SavaUpLoadWebFile")
    @ApiOperation("保存上传文件资源路径")
    @Token
    public R SavaUpLoadWebFile(@RequestBody SavaUpLoadWebFileVo savaUpLoadWebFileVo) {
        List<SystemImgs> urlList = savaUpLoadWebFileVo.getUrlList();
        Date date = new Date();
        for (SystemImgs systemImgs : urlList) {
            systemImgs.setCreationTime(date);
            systemImgsMapper.insert(systemImgs);
        }
        return R.succeed(savaUpLoadWebFileVo);
    }


    @PostMapping("/getWebFile")
    @ApiOperation("获取网站资源")
    @Token
    public R getWebFile(@RequestBody SystemImgs systemImgs) {
        List<SystemImgs> list = systemImgsMapper.list(systemImgs);
        return R.succeed(list);
    }


    @PostMapping("/editByIdFile")
    @ApiOperation("修改文件信息")
    public R editByIdFile(@RequestBody SystemImgs systemImgs) {
        systemImgsMapper.updateById(systemImgs);
        return R.succeed();
    }

    @GetMapping("/delFile")
    @ApiOperation("修改文件")
    public R delFile(@RequestParam("id") Integer id) {
        systemImgsMapper.deleteById(id);
        return R.succeed(id);
    }

    @GetMapping("getWebInfo")
    @ApiOperation("获取网站信息")
    public R getWebInfo(@RequestParam(value = "type",required = false) String type) {
        WebConfig webConfig = webConfigMapper.selectById(1);
        if (type ==null || !type.equals("all")) {
            webConfig.setBrief(null);
        }
        return R.succeed(webConfig);
    }


    @PostMapping("savaWebInfo")
    @ApiOperation("保存网站信息")
    public R savaWebInfo(@RequestBody WebConfig webConfig) {
        webConfig.setId(1);
        webConfigMapper.updateById(webConfig);
        return R.succeed();
    }
}
