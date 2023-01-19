package com.blogs.controller;


import com.blogs.annotation.Token;
import com.blogs.model.article.Article;
import com.blogs.model.article.ArticleComment;
import com.blogs.service.ArticleService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.PageVo;
import com.blogs.vo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

import static com.blogs.util.IPUtil.getIpAddr;

@RestController
@RequestMapping("/api/article")
@Api(tags = "网站文章相关")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @PostMapping("/add")
    @ApiOperation("添加文章")
    @Token
    public R addArticle(@RequestBody Article article) throws Exception {
        article.setCreationTime(new Date());
        article.setUserId(CurrentUserUtil.getUserId());
        articleService.addArticle(article);
        return R.succeed();
    }


    @GetMapping("/info")
    @ApiOperation("根据ID获取文章")
    public R getArticle(@RequestParam("id") Integer id) {
        Article article = articleService.getArticleById(id);
        return R.succeed(article);
    }


    @GetMapping("/del")
    @ApiOperation("删除文章")
    @Token
    public R articleDel(@RequestParam("id") Integer id) {
        articleService.articleDel(id);
        return R.succeed();
    }


    @PostMapping("/up")
    @ApiOperation("编辑文章")
    @Token
    public R upArticle(@RequestBody Article article) {
        articleService.upArticle(article);
        return R.succeed();
    }


    @GetMapping("/list")
    @ApiOperation("获取根据条件文章列表")
    public R getArticleList(@RequestParam(value = "oneClassId", required = false) Integer oneClassId,
                            @RequestParam(value = "twoClassId", required = false) Integer twoClassId,
                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                            @RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "labelId", required = false) Integer labelId,
                            @RequestParam(value = "type", required = false,defaultValue = "all") String type
    ) {
        PageVo articleList = articleService.getArticleList(oneClassId, twoClassId, page, pageSize,id,title,labelId,type);
        return R.succeed(articleList);
    }


    @GetMapping("/homeRecommend")
    @ApiOperation("设置首页推荐")
    @Token
    public R setHomeRecommend(@RequestParam("id") Integer id) throws Exception {
        articleService.setHomeRecommend(id);
        return R.succeed();
    }

    @GetMapping("/getHomeRecommend")
    @ApiOperation("获取首页推荐")
    public R getHomeRecommend(@RequestParam(value = "page", defaultValue = "1") Integer page,
                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        PageVo homeRecommend = articleService.getHomeRecommend(page, pageSize);
        return R.succeed(homeRecommend);
    }


    @GetMapping("/getComment")
    @ApiOperation("获取文章评论")
    public R getComment(@RequestParam("id") Integer id) {
        Map<String, Object> comment = articleService.getComment(id);
        return  R.succeed(comment);
    }

    @PostMapping("pushComment")
    @ApiOperation("发表文章评论(userId)")
    public R pushComment(@RequestBody ArticleComment articleComment, HttpServletRequest request) {
        String ipAddr = getIpAddr(request);
        articleComment.setCreationTime(new Date());
        articleComment.setIp(ipAddr);
        articleService.pushComment(articleComment);
        return R.succeed();
    }


    @PostMapping("upComment")
    @ApiOperation("编辑评论")
    @Token
    public R upComment(@RequestBody ArticleComment articleComment) {
        articleService.upComment(articleComment);
        return R.succeed();
    }

    @GetMapping("/statistics")
    @ApiOperation("文章统计")
    public R statistics() {
        Map<String, Object> statistics = articleService.statistics();
        return R.succeed(statistics);
    }
}
