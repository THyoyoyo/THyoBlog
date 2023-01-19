package com.blogs.mapper.article;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.article.ArticleOneClass;
import com.blogs.vo.article.ArticleOneClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ArticleOneClassMapper extends BaseMapper<ArticleOneClass> {

  List<ArticleOneClass> getArticleOneClassAll(@Param("id") Integer id);

}