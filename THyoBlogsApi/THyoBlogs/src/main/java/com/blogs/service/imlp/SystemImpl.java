package com.blogs.service.imlp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blogs.mapper.system.SystemMenusMapper;
import com.blogs.mapper.system.SystemRoleInfoMapper;
import com.blogs.mapper.system.SystemRoleMapper;
import com.blogs.model.system.SystemMenus;
import com.blogs.model.system.SystemRole;
import com.blogs.model.system.SystemRoleInfo;
import com.blogs.service.SystemService;
import com.blogs.util.CurrentUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class SystemImpl implements SystemService {

    @Autowired
    SystemMenusMapper systemMenusMapper;

    @Autowired
    SystemRoleInfoMapper systemRoleInfoMapper;

    @Autowired
    SystemRoleMapper systemRoleMapper;



    @Override
    public List<SystemMenus> getSystemMenus(String type) {

        QueryWrapper<SystemMenus> systemMenusQueryWrapper = new QueryWrapper<>();
        systemMenusQueryWrapper.isNull("parent_id");
        List<SystemMenus> systemMenus = systemMenusMapper.selectList(systemMenusQueryWrapper);
        if(type.equals("all")){
            for (SystemMenus systemMenu : systemMenus) {
                QueryWrapper<SystemMenus> systemMenusQueryWrapper1 = new QueryWrapper<>();
                systemMenusQueryWrapper1.eq("parent_id",systemMenu.getId());
                systemMenu.setChilds(systemMenusMapper.selectList(systemMenusQueryWrapper1));
            }
        }
        return systemMenus;
    }

    @Override
    public void systemSava(SystemMenus systemMenus) throws Exception {
       if(systemMenus.getId() != null){
           systemMenusMapper.updateById(systemMenus);
       }else {
           systemMenus.setCreationTime(new Date());
           systemMenus.setUserId(CurrentUserUtil.getUserId());
           systemMenusMapper.insert(systemMenus);
       }
    }

    @Override
    public void systemDel(Integer id) {
        systemMenusMapper.deleteById(id);
    }



    @Override
    public List<SystemRoleInfo> getSystemRole() {
        QueryWrapper<SystemRoleInfo> systemRoleInfoQueryWrapper = new QueryWrapper<>();
        List<SystemRoleInfo> systemRoleInfos = systemRoleInfoMapper.selectList(systemRoleInfoQueryWrapper);
        for (SystemRoleInfo systemRoleInfo : systemRoleInfos) {
            List<Integer> listByMenuId = systemRoleMapper.getListByMenuId(systemRoleInfo.getId());
            systemRoleInfo.setMenuIds(listByMenuId);
        }
        return systemRoleInfos;
    }

    @Override
    public void roleSava(SystemRoleInfo systemRoleInfo) throws Exception {
      if(systemRoleInfo.getId() == null){
          systemRoleInfo.setCreationTime(new Date());
          systemRoleInfo.setUserId(CurrentUserUtil.getUserId());
          systemRoleInfoMapper.insert(systemRoleInfo);

          for (Integer menuId : systemRoleInfo.getMenuIds()) {
              SystemRole systemRole = new SystemRole();
              systemRole.setRoleId(systemRoleInfo.getId());
              systemRole.setMenusId(menuId);
              systemRoleMapper.insert(systemRole);
          }

      }else {
          systemRoleInfoMapper.updateById(systemRoleInfo);
          //删除
          QueryWrapper<SystemRole> systemRoleQueryWrapper = new QueryWrapper<>();
          systemRoleQueryWrapper.eq("role_id",systemRoleInfo.getId());
          systemRoleMapper.delete(systemRoleQueryWrapper);
          //添加
          for (Integer menuId : systemRoleInfo.getMenuIds()) {
              SystemRole systemRole = new SystemRole();
              systemRole.setRoleId(systemRoleInfo.getId());
              systemRole.setMenusId(menuId);
              systemRoleMapper.insert(systemRole);
          }
      }
    }

    @Override
    public void roleDel(Integer id) {
        systemRoleInfoMapper.deleteById(id);
    }

    @Override
    public  List<SystemMenus> getRoleMenus(Integer id) throws Exception {
        QueryWrapper<SystemRole> systemRoleQueryWrapper = new QueryWrapper<>();
        systemRoleQueryWrapper.eq("role_id",id);
        List<SystemRole> systemRoles = systemRoleMapper.selectList(systemRoleQueryWrapper);
        List<SystemMenus> menus = new ArrayList<>();

        for (SystemRole systemRole : systemRoles) {
            QueryWrapper<SystemMenus> systemMenusQueryWrapper = new QueryWrapper<>();
            systemMenusQueryWrapper.eq("id",systemRole.getMenusId()).last("limit 0,1");
            SystemMenus systemMenus = systemMenusMapper.selectOne(systemMenusQueryWrapper);
            //判断是否存在父级
            if(systemMenus.getParentId() ==null){
            //判断 menus 已存在相同父级
                boolean contains = menus.stream().anyMatch(m -> m.getId().equals(systemMenus.getId()));
                if(!contains){
                    menus.add(systemMenus);
                }
            }else {
                //如果存在 查询父级
                QueryWrapper<SystemMenus> systemMenusQueryWrapper1 = new QueryWrapper<>();
                systemMenusQueryWrapper1.eq("id",systemMenus.getParentId()).last("limit 0,1");
                SystemMenus systemMenus1 = systemMenusMapper.selectOne(systemMenusQueryWrapper1);
                //判断 menus 中 已存在相同父级
                boolean contains = menus.stream().anyMatch(m -> m.getId().equals(systemMenus1.getId()));
                if(!contains){
                    List<SystemMenus> childs = systemMenus1.getChilds();
                    if(childs == null){
                        childs = new ArrayList<SystemMenus>();
                    }
                    childs.add(systemMenus);
                    systemMenus1.setChilds(childs);
                    menus.add(systemMenus1);
                }else {
                    Integer i = null;
                    for (int k = 0; k < menus.size(); k++) {
                        SystemMenus systemMenus2 = menus.get(k);
                        if(systemMenus2.getId().equals(systemMenus1.getId())){
                            i = k;
                        }
                    }
                    SystemMenus systemMenus2 = menus.get(i);
                    List<SystemMenus> childs = systemMenus2.getChilds();
                    if(childs == null){
                        childs = new ArrayList<SystemMenus>();
                    }
                    childs.add(systemMenus);
                    //根据sort排序
                    childs.sort((x1,x2)->x1.getSort().compareTo(x2.getSort()));
                    systemMenus2.setChilds(childs);
                    menus.set(i,systemMenus2);
                }
            }
        }

        //根据sort排序
        menus.sort((x1,x2)->x1.getSort().compareTo(x2.getSort()));
        return menus;
    }
}
