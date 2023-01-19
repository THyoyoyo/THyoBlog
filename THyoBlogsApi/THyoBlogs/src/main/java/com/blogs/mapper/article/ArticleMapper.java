package com.blogs.mapper.article;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.model.article.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    IPage<Article> getList(Page<Article> page, @Param("params") Map<String,Object> param );

    Article getArticleInfo(@Param("id") Integer id);
 }