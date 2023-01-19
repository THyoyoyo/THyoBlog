package com.blogs.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.model.article.ArticleOneClass;
import com.blogs.model.article.ArticleTwoClass;
import com.blogs.vo.common.PageVo;


public interface ArticleClassService {

    public void classAddOne(ArticleOneClass articleOneClass);
    public void classUpOne(ArticleOneClass articleOneClass);
    public void classDelOne(Integer id);
    public PageVo getClassOneList(Integer page, Integer pageSize);

    public void classAddTwo(ArticleTwoClass articleTwoClass);
    public void classUpTwo(ArticleTwoClass articleTwoClass);
    public void classDelTwo(Integer id);
    public PageVo getClassTwoList(Integer page,Integer pageSize);
}
