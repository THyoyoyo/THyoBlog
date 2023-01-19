package com.blogs.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.annotation.Token;
import com.blogs.mapper.article.ArticleOneClassMapper;
import com.blogs.mapper.article.ArticleTwoClassMapper;
import com.blogs.model.article.ArticleOneClass;
import com.blogs.model.article.ArticleTwoClass;
import com.blogs.service.ArticleClassService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.article.ArticleOneClassVo;
import com.blogs.vo.article.ArticleTwoClassVo;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/article/class")
@Api(tags = "文章分类管理")
public class ArticleClassController {

    @Autowired
    ArticleClassService articleClassService;

    @Autowired
    ArticleTwoClassMapper articleTwoClassMapper;

    @Autowired
    ArticleOneClassMapper articleOneClassMapper;

    @GetMapping("/list")
    @ApiOperation("获取分类列表")
    public R getClassList() throws Exception {
        List<ArticleOneClass> articleOneClassAll = articleOneClassMapper.getArticleOneClassAll(null);
        for (ArticleOneClass articleOneClass : articleOneClassAll) {
            articleOneClass.setList(articleTwoClassMapper.getArticleTwoClassAll(articleOneClass.getId()));
        }
        return R.succeed(articleOneClassAll);
    }

    @PostMapping("/addOne")
    @ApiOperation("添加一级分类")
    @Token
    public R classAddOne(@RequestBody ArticleOneClass articleOneClass) throws Exception {
        articleOneClass.setCreationTime(new Date());
        articleOneClass.setUserId(CurrentUserUtil.getUserId());
        articleClassService.classAddOne(articleOneClass);
        return R.succeed();
    }

    @PostMapping("/addTwo")
    @ApiOperation("添加二级分类")
    @Token
    public R classAddTwo(@RequestBody ArticleTwoClass articleTwoClass) {
        articleTwoClass.setCreationTime(new Date());
        articleClassService.classAddTwo(articleTwoClass);
        return R.succeed();
    }


    @PostMapping("/upOne")
    @ApiOperation("编辑一级分类")
    @Token
    public R classUpOne(@RequestBody ArticleOneClass articleOneClass) {
        articleClassService.classUpOne(articleOneClass);
        return R.succeed();
    }

    @PostMapping("/upTwo")
    @ApiOperation("编辑二级分类")
    @Token
    public R classUpTwo(@RequestBody ArticleTwoClass articleTwoClass) {
        articleClassService.classUpTwo(articleTwoClass);
        return R.succeed();
    }

    @GetMapping("/delOne")
    @ApiOperation("删除分类一")
    @Token
    public R classDelOne(@RequestParam("id") Integer id) {
        articleClassService.classDelOne(id);
        return R.succeed();
    }

    @GetMapping("/delTwo")
    @ApiOperation("删除分类二")
    @Token
    public R classDelTwo(@RequestParam("id") Integer id) {
        articleClassService.classDelTwo(id);
        return R.succeed();
    }
}
