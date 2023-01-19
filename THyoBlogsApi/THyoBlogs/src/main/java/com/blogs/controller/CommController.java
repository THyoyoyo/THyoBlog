package com.blogs.controller;


import com.blogs.service.CommService;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comm")
@Api(tags = "公共模块")
public class CommController {

    @Autowired
    CommService commService;

    @ApiOperation(value = "获取上传token")
    @GetMapping("/getQlyToken")
    public R getQlyToken(){
        String uploadFileToken = commService.getUploadFileToken();
         return  R.succeed(uploadFileToken);
    }
}
