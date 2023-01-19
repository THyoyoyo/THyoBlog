package com.blogs.model.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.blogs.model.system.SystemMenus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("user")
@Data
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "签名")
    private String signature;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "帐号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "秘钥")
    private String token;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "角色Id")
    private Integer roleId;

    @ApiModelProperty(value = "创建时间")
    private Date creationTime;

    @ApiModelProperty(value = "登录时间")
    private Date lastTime;
}
