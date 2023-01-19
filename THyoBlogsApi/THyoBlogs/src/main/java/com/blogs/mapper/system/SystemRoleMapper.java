package com.blogs.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blogs.model.system.SystemRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
   List<Integer> getListByMenuId(@Param("roleId") Integer id);
}