package com.blogs.controller;

import com.blogs.annotation.Token;
import com.blogs.model.article.ArticleLabelInfo;
import com.blogs.service.ArticlelabelService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.PageVo;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/article/label")
@Api(tags = "文章标签管理")
public class ArticleLabelController {

    @Autowired
    ArticlelabelService articlelabelService;

    @GetMapping("/list")
    @ApiOperation("获取标签列表")
    public R getlabel(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) throws Exception {
        PageVo list = articlelabelService.list(page, pageSize);
         return   R.succeed(list);
    }

    @PostMapping("/add")
    @ApiOperation("添加标签")
    @Token
    public R labelAdd(@RequestBody ArticleLabelInfo articleLabelInfo) throws Exception {
        articleLabelInfo.setCreationTime(new Date());
        articleLabelInfo.setUserId(CurrentUserUtil.getUserId());
        articlelabelService.add(articleLabelInfo);
        return R.succeed();
    }

    @PostMapping("/up")
    @ApiOperation("编辑标签")
    @Token
    public R labelUp(@RequestBody ArticleLabelInfo articleLabelInfo) {
        articlelabelService.up(articleLabelInfo);
        return R.succeed();
    }

    @GetMapping("/del")
    @ApiOperation("删除标签列表")
    @Token
    public R dellabel(@RequestParam("id") Integer id) {
        articlelabelService.del(id);
        return R.succeed();
    }

}
