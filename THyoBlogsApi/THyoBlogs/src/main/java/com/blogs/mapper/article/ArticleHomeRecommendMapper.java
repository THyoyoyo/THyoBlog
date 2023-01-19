package com.blogs.mapper.article;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.blogs.model.article.Article;
import com.blogs.model.article.ArticleHomeRecommend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleHomeRecommendMapper extends BaseMapper<ArticleHomeRecommend> {

    IPage<Map<String,Object>> getList(IPage<Map<String,Object>> page);

}