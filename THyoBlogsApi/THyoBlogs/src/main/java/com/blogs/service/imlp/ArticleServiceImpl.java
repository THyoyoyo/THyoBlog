package com.blogs.service.imlp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.mapper.article.*;
import com.blogs.mapper.user.UserMapper;
import com.blogs.model.article.*;
import com.blogs.model.user.User;
import com.blogs.service.ArticleService;
import com.blogs.util.CurrentUserUtil;
import com.blogs.vo.common.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleLabelMapper articleLabelMapper;

    @Autowired
    ArticleLabelInfoMapper articleLabelInfoMapper;

    @Autowired
    ArticleHomeRecommendMapper articleHomeRecommendMapper;

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Autowired
    UserMapper userMapper;


    @Override
    public void addArticle(Article article) {
        int insert = articleMapper.insert(article);
        List<Integer> labels = article.getLabels();
        //存储标签
        for (Integer label : labels) {
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setLabelId(label);
            articleLabel.setArticleId(article.getId());
            articleLabelMapper.insert(articleLabel);
        }
    }

    @Override
    public Article getArticleById(Integer id) {
        Article article = articleMapper.getArticleInfo(id);
        //更新查看数量
        article.setViewNum(article.getViewNum()+1);
        articleMapper.updateById(article);
        //标签查询
        QueryWrapper<ArticleLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", id);
        List<ArticleLabel> articleLabels = articleLabelMapper.selectList(queryWrapper);
        List<ArticleLabelInfo> articleLabelInfos = new ArrayList<>();
        for (ArticleLabel articleLabel : articleLabels) {
            ArticleLabelInfo articleLabelInfo = articleLabelInfoMapper.selectById(articleLabel.getLabelId());
            articleLabelInfos.add(articleLabelInfo);
        }
        article.setLabelName(articleLabelInfos);
        return article;
    }

    @Override
    public void articleDel(Integer id) {
        QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
        articleLabelQueryWrapper.eq("article_id",id);
        articleLabelMapper.delete(articleLabelQueryWrapper);

        QueryWrapper<ArticleComment> articleCommentQueryWrapper = new QueryWrapper<>();
        articleCommentQueryWrapper.eq("article_id",id);
        articleCommentMapper.delete(articleCommentQueryWrapper);

        QueryWrapper<ArticleHomeRecommend> articleHomeRecommendQueryWrapper = new QueryWrapper<>();
        articleHomeRecommendQueryWrapper.eq("article_id",id);
        articleHomeRecommendMapper.delete(articleHomeRecommendQueryWrapper);

        articleMapper.deleteById(id);
    }

    @Override
    public void upArticle(Article article) {
        articleMapper.updateById(article);
        //删除文章标签
        Article selectArticle1 = articleMapper.selectById(article.getId());
        List<Integer> labels = selectArticle1.getLabels();
        QueryWrapper<ArticleLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("article_id", article.getId());
        articleLabelMapper.delete(queryWrapper);
        //存储标签
        for (Integer label : article.getLabels()) {
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setLabelId(label);
            articleLabel.setArticleId(article.getId());
            articleLabelMapper.insert(articleLabel);
        }
    }

    @Override
    public PageVo getArticleList(Integer oneClassId, Integer twoClassId, Integer page, Integer pageSize,Integer id ,String title,Integer labelId ,String type) {
        Page<Article> pageInfo = new Page<>(page, pageSize);
        HashMap<String, Object> map = new HashMap<>();
        map.put("oneClassId",oneClassId);
        map.put("twoClassId",twoClassId);
        map.put("id",id);
        map.put("title",title);
        map.put("labelId",labelId);
        articleMapper.getList(pageInfo, map);
        for (Article article : pageInfo.getRecords()) {
            if (!type.equals("all")){
                article.setContent("");
            }
            //创建标签
            QueryWrapper<ArticleLabel> labelQueryWrapper = new QueryWrapper<>();
            labelQueryWrapper.eq("article_id",article.getId());
            List<ArticleLabel> articleLabels = articleLabelMapper.selectList(labelQueryWrapper);
            ArrayList<ArticleLabelInfo> articleLabelInfos = new ArrayList<>();
            List<Integer> labels = new ArrayList<>();
            for (ArticleLabel articleLabel : articleLabels) {
                ArticleLabelInfo articleLabelInfo = articleLabelInfoMapper.selectById(articleLabel.getLabelId());
                articleLabelInfos.add(articleLabelInfo);
                labels.add(articleLabel.getLabelId());
            }
            article.setLabelName(articleLabelInfos);
            article.setLabels(labels);

           // 获取首条评论
            QueryWrapper<ArticleComment> articleCommentQueryWrapper = new QueryWrapper<>();
            articleCommentQueryWrapper.eq("article_id",article.getId())
                    .isNull("parent_id")
                    .orderByDesc("creation_time")
                    .last("limit 0,1");
            article.setLatestComments(articleCommentMapper.selectOne(articleCommentQueryWrapper));          ;
        }


        PageVo<Article> PageVo = new PageVo<>();
        PageVo.setList(pageInfo.getRecords());
        PageVo.setPages(pageInfo.getPages());
        PageVo.setTotal(pageInfo.getTotal());
        PageVo.setPageSize(pageInfo.getSize());
        PageVo.setPageNum(pageInfo.getCurrent());
        return PageVo;
    }

    @Override
    public void setHomeRecommend(Integer id) throws Exception {
        QueryWrapper<ArticleHomeRecommend> recommendQueryWrapper = new QueryWrapper<>();
        recommendQueryWrapper.eq("article_id",id);
        ArticleHomeRecommend select = articleHomeRecommendMapper.selectOne(recommendQueryWrapper);

        if(select !=null){
            articleHomeRecommendMapper.deleteById(select.getId());
        }else {
            ArticleHomeRecommend articleHomeRecommend = new ArticleHomeRecommend();
            articleHomeRecommend.setArticleId(id);
            articleHomeRecommend.setCreationTime(new Date());
            //查询用户ID
            articleHomeRecommend.setUserId(CurrentUserUtil.getUserId());
            articleHomeRecommendMapper.insert(articleHomeRecommend);
        }
    }

    @Override
    public PageVo getHomeRecommend( Integer page, Integer pageSize) {
        Page<Map<String, Object>> pageInfo = new Page<>(page, pageSize);
        IPage<Map<String, Object>> list = articleHomeRecommendMapper.getList(pageInfo);

        for (Map<String, Object> article : pageInfo.getRecords()) {
            //文章标签
            QueryWrapper<ArticleLabel> labelQueryWrapper = new QueryWrapper<>();
            labelQueryWrapper.eq("article_id",article.get("id"));
            List<ArticleLabel> articleLabels = articleLabelMapper.selectList(labelQueryWrapper);
            ArrayList<ArticleLabelInfo> articleLabelInfos = new ArrayList<>();
            for (ArticleLabel articleLabel : articleLabels) {
                ArticleLabelInfo articleLabelInfo = articleLabelInfoMapper.selectById(articleLabel.getLabelId());
                articleLabelInfos.add(articleLabelInfo);
            }
            article.put("labelName",articleLabelInfos);

            //最新评论
            QueryWrapper<ArticleComment> articleCommentQueryWrapper = new QueryWrapper<>();
            articleCommentQueryWrapper.eq("article_id",article.get("id")).isNull("parent_id").orderByDesc("creation_time").last("limit 1");
            ArticleComment articleComment = articleCommentMapper.selectOne(articleCommentQueryWrapper);
            article.put("newComments",articleComment);

        }

        PageVo<Map<String,Object>> PageVo = new PageVo<>();
        PageVo.setList(pageInfo.getRecords());
        PageVo.setPages(pageInfo.getPages());
        PageVo.setTotal(pageInfo.getTotal());
        PageVo.setPageSize(pageInfo.getSize());
        PageVo.setPageNum(pageInfo.getCurrent());
        return PageVo;
    }

    @Override
    public  Map<String, Object> getComment(Integer id) {
        QueryWrapper<ArticleComment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("article_id",id).isNull("parent_id").orderByDesc("creation_time");
        List<ArticleComment> articleComments = articleCommentMapper.selectList(commentQueryWrapper);

        for (ArticleComment articleComment : articleComments) {
            QueryWrapper<ArticleComment> commentQueryWrapper1 = new QueryWrapper<>();
            commentQueryWrapper1.eq("parent_id",articleComment.getId());
            articleComment.setReplyList(articleCommentMapper.selectList(commentQueryWrapper1));

            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("id",articleComment.getArticleId());
            Article article = articleMapper.selectOne(articleQueryWrapper);
            articleComment.setTitle(article.getTitle());
        }
         Map<String, Object> map = new HashMap<>();
         map.put("data",articleComments);
         return  map;
    }

    @Override
    public void pushComment(ArticleComment articleComment) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id", articleComment.getUserId());
        User user = userMapper.selectOne(userQueryWrapper);
        articleComment.setUserName(user.getName());
        articleCommentMapper.insert(articleComment);
    }

    @Override
    public void upComment(ArticleComment articleComment) {
        articleCommentMapper.updateById(articleComment);
    }


    @Override
    public Map<String, Object> statistics() {
        HashMap<String, Object> map = new HashMap<>();

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        Integer integer = articleMapper.selectCount(articleQueryWrapper);
        map.put("total",integer);
        return map;
    }
}
