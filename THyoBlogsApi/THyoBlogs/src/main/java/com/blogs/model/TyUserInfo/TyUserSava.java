package com.blogs.model.TyUserInfo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TyUserSava {


    /** qq */
    @ApiModelProperty(value = "id")
    private Integer id;

    /** qq */
    @ApiModelProperty(value = "qq")
    private String qq;


    /** 腾游用户id */
    @ApiModelProperty(value = "腾游用户id")
    private String userId;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    private String password;


    /** 确认密码 */
    @ApiModelProperty(value = "确认密码")
    private String checkPassword;


    /** 自动暂停时间 */
    @ApiModelProperty(value = "自动暂停时间")
    private String autoCloseTime;

    /** 腾游用户token */
    @ApiModelProperty(value = "腾游用户token")
    private String token;

    /** 其他信息 */
    @ApiModelProperty(value = "其他信息")
    private String json;

}
