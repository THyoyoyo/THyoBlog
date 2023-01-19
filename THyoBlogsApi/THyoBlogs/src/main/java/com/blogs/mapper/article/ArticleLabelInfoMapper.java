package com.blogs.mapper.article;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.model.article.Article;
import com.blogs.model.article.ArticleLabelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleLabelInfoMapper extends BaseMapper<ArticleLabelInfo> {
    IPage<ArticleLabelInfo> list(Page<ArticleLabelInfo> page, @Param("params") Map<String,Object> param);
}