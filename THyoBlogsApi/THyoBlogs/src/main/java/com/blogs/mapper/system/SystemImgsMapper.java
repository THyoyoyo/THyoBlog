package com.blogs.mapper.system;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.blogs.model.system.SystemImgs;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SystemImgsMapper extends BaseMapper<SystemImgs> {
    List<SystemImgs> list(@Param("params") SystemImgs param);
}