package com.blogs.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogs.mapper.user.UserMapper;
import com.blogs.model.user.User;
import com.blogs.service.UserService;
import com.blogs.vo.common.PageVo;
import com.blogs.vo.user.UserVo;
import com.blogs.vo.user.userListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public UserVo getUserInfo(Integer id) {
        User user = userMapper.selectById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }

    @Override
    public PageVo getUsetList(Integer page,Integer pageSzie) {
        //创建分页拦截器
        Page<userListVo> pageInfo = new Page<>(page, pageSzie);

        userMapper.getList(pageInfo);

        PageVo<userListVo> PageVo = new PageVo<>();
        PageVo.setList(pageInfo.getRecords());
        PageVo.setPages(pageInfo.getPages());
        PageVo.setTotal(pageInfo.getTotal());
        PageVo.setPageSize(pageInfo.getSize());
        PageVo.setPageNum(pageInfo.getCurrent());
        return PageVo;
    }

    @Override
    public void addUser(User user) {
        user.setCreationTime(new Date());
         userMapper.insert(user);
    }

    @Override
    public void upUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void delUser(Integer id) {
          userMapper.deleteById(id);
    }
}
