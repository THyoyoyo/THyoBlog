package com.blogs.vo.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.blogs.model.system.SystemMenus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserInfoVo {
    private Integer id;
    private String name;
    private Integer age;
    private String signature;
    private String head;
    private String account;
    private String token;
    private String state;
    private Date creationTime;
    private Date lastTime;
    private List<SystemMenus> menus;
}
