package com.blogs.mapper.TyUserInfo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.blogs.model.TyUserInfo.TyUserInfo;

public interface TyUserInfoMapper extends BaseMapper<TyUserInfo> {

	Integer count(@Param("params") Map<String, Object> params);
	List<TyUserInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset, @Param("limit") Integer limit);

}