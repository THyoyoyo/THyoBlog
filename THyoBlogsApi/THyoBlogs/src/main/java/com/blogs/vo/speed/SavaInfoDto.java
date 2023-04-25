package com.blogs.vo.speed;


import com.blogs.model.speed.SpeedInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SavaInfoDto extends SpeedInfo{


    @ApiModelProperty(value = "帐号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "QQ号")
    private String qq;

}
