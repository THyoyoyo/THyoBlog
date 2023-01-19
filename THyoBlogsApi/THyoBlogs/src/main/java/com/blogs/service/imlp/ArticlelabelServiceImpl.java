package com.blogs.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.mapper.article.ArticleLabelInfoMapper;
import com.blogs.model.article.ArticleLabel;
import com.blogs.model.article.ArticleLabelInfo;
import com.blogs.service.ArticlelabelService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
public class ArticlelabelServiceImpl implements ArticlelabelService {
    @Autowired
    ArticleLabelInfoMapper articleLabelInfoMapper;

    @Override
    public PageVo list(Integer page, Integer size) throws Exception {
        Page<ArticleLabelInfo> pageInfo = new Page<>(page, size);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", CurrentUserUtil.getUserId());
        articleLabelInfoMapper.list(pageInfo, map);
        PageVo<ArticleLabelInfo> PageVo = new PageVo<>();
        PageVo.setList(pageInfo.getRecords());
        PageVo.setPages(pageInfo.getPages());
        PageVo.setTotal(pageInfo.getTotal());
        PageVo.setPageSize(pageInfo.getSize());
        PageVo.setPageNum(pageInfo.getCurrent());
        return PageVo;
    }

    @Override
    public void add(ArticleLabelInfo articleLabel) {
        articleLabelInfoMapper.insert(articleLabel);
    }

    @Override
    public void up(ArticleLabelInfo articleLabel) {
        articleLabelInfoMapper.updateById(articleLabel);
    }

    @Override
    public void del(Integer id) {
        articleLabelInfoMapper.deleteById(id);
    }
}
