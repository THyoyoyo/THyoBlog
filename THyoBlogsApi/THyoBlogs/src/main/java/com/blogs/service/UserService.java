package com.blogs.service;


import com.blogs.model.user.User;
import com.blogs.vo.common.PageVo;
import com.blogs.vo.user.UserVo;

public interface UserService {
    public UserVo getUserInfo(Integer id );

    public PageVo getUsetList(Integer page, Integer pageSzie);

    public void addUser(User user);

    public void upUser(User user);

    public  void  delUser(Integer id);
}
