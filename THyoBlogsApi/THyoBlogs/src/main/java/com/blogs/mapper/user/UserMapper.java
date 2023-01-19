package com.blogs.mapper.user;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.model.user.User;
import com.blogs.vo.user.userListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<userListVo> getList(IPage<userListVo> page);

}
