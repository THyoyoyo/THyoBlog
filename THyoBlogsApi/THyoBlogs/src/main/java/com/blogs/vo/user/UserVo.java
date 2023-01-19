package com.blogs.vo.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserVo {
    private Integer id;
    private String name;
    private Integer age;
    private String signature;
    private String head;
    private String account;
    private String password;
    private String token;
    private String state;
    private Date creationTime;
    private Date lastTime;
}
