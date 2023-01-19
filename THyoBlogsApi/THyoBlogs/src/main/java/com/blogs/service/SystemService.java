package com.blogs.service;

import com.blogs.model.system.SystemMenus;
import com.blogs.model.system.SystemRole;
import com.blogs.model.system.SystemRoleInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SystemService {
    /**
     * 菜单列表查询
     */
    public List<SystemMenus> getSystemMenus(String type);

    /**
     * 菜单新增、修改
     */
    public void systemSava(SystemMenus systemMenus) throws Exception;

    /**
     * 菜单删除
     */
    public void systemDel(Integer id);


    /**
     * 角色列表查询
     */
    public List<SystemRoleInfo> getSystemRole();

    /**
     * 角色新增、修改
     */
    public void roleSava(SystemRoleInfo systemRoleInfo) throws Exception;

    /**
     * 角色删除
     */
    public void roleDel(Integer id);

    /**
     * 获取角色拥有的权限
     */
    public List<SystemMenus> getRoleMenus(Integer id) throws Exception;

}
