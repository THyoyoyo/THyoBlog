package com.blogs.mapper.article;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.article.ArticleTwoClass;
import com.blogs.vo.article.ArticleTwoClassVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface ArticleTwoClassMapper extends BaseMapper<ArticleTwoClass> {

   List<ArticleTwoClass> getArticleTwoClassAll(@Param("id") Integer id);

}