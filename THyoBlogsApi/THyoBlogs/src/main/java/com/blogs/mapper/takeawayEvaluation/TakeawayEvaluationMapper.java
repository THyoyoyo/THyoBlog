package com.blogs.mapper.takeawayEvaluation;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.takeawayEvaluation.TakeawayEvaluation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TakeawayEvaluationMapper extends BaseMapper<TakeawayEvaluation> {

    void selectList();
}