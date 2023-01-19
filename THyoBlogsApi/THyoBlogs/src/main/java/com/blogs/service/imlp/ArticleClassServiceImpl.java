package com.blogs.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.mapper.article.ArticleOneClassMapper;
import com.blogs.mapper.article.ArticleTwoClassMapper;
import com.blogs.model.article.ArticleOneClass;
import com.blogs.model.article.ArticleTwoClass;
import com.blogs.service.ArticleClassService;
import com.blogs.vo.common.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ArticleClassServiceImpl implements ArticleClassService {

    @Autowired
    ArticleOneClassMapper articleOneClassMapper;

    @Autowired
    ArticleTwoClassMapper articleTwoClassMapper;

    @Override
    public void classAddOne(ArticleOneClass articleOneClass) {
        articleOneClassMapper.insert(articleOneClass);
    }

    @Override
    public void classUpOne(ArticleOneClass articleOneClass) {
        articleOneClassMapper.updateById(articleOneClass);
    }

    @Override
    public void classDelOne(Integer id) {
        articleOneClassMapper.deleteById(id);
    }

    @Override
    public PageVo getClassOneList(Integer page,Integer pageSize) {
        Page<ArticleOneClass> pageInfo = new Page<>(page,pageSize);
        QueryWrapper<ArticleOneClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",1);
        articleOneClassMapper.selectPage(pageInfo,queryWrapper);
        PageVo<ArticleOneClass> PageVo = new PageVo<>();
        PageVo.setList(pageInfo.getRecords());
        PageVo.setPages(pageInfo.getPages());
        PageVo.setTotal(pageInfo.getTotal());
        PageVo.setPageSize(pageInfo.getSize());
        PageVo.setPageNum(pageInfo.getCurrent());
        return PageVo;
    }

    @Override
    public void classAddTwo(ArticleTwoClass articleTwoClass) {
           articleTwoClassMapper.insert(articleTwoClass);
    }

    @Override
    public void classUpTwo(ArticleTwoClass articleTwoClass) {
        articleTwoClassMapper.updateById(articleTwoClass);
    }

    @Override
    public void classDelTwo(Integer id) {
        articleTwoClassMapper.deleteById(id);
    }

    @Override
    public PageVo getClassTwoList(Integer page,Integer pageSize) {
        Page<ArticleTwoClass> pageInfo = new Page<>(page,pageSize);
        QueryWrapper<ArticleTwoClass> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",1);
        articleTwoClassMapper.selectPage(pageInfo,queryWrapper);
        PageVo<ArticleTwoClass> PageVo = new PageVo<>();
        PageVo.setList(pageInfo.getRecords());
        PageVo.setPages(pageInfo.getPages());
        PageVo.setTotal(pageInfo.getTotal());
        PageVo.setPageSize(pageInfo.getSize());
        PageVo.setPageNum(pageInfo.getCurrent());
        return PageVo;
    }
}
