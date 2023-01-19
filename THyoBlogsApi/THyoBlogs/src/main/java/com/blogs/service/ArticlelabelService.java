package com.blogs.service;


import com.blogs.model.article.ArticleLabelInfo;
import com.blogs.vo.common.PageVo;

public interface ArticlelabelService {

     public PageVo list(Integer page, Integer Size) throws Exception;
     public void add(ArticleLabelInfo articleLabel);
     public void up(ArticleLabelInfo articleLabel);
     public void del(Integer id );
}
