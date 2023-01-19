package com.blogs.service;

import com.blogs.model.article.Article;
import com.blogs.model.article.ArticleComment;
import com.blogs.vo.common.PageVo;
import io.swagger.models.auth.In;

import java.util.Map;

public interface ArticleService {
    public void addArticle(Article article);

    public Article getArticleById(Integer id);

    public void articleDel(Integer id);

    public void upArticle(Article article);

    public PageVo getArticleList(Integer oneClassId , Integer twoClassId, Integer page, Integer pageSize,Integer id, String title,Integer labelId,String type);

    public void  setHomeRecommend(Integer id) throws Exception;

    public PageVo  getHomeRecommend( Integer page, Integer pageSize);

    public Map<String, Object> getComment(Integer id);

    public void pushComment(ArticleComment articleComment);

    public void upComment(ArticleComment articleComment);

    public Map<String, Object> statistics();
}
