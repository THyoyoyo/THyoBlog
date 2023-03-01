package com.blogs.model.TyUserInfo;



import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class TyUserList {
    /** 自增id */

    @ApiModelProperty(value = "自增id")
    private Integer id;

    /** 腾游用户id */
    @ApiModelProperty(value = "腾游用户id")
    private String userId;

    /** 其他信息 */
    @ApiModelProperty(value = "状态: 0 关闭 1启动")
    private Integer state;

    /** 创建时间 */
    @ApiModelProperty(value = "创建时间")
    private Date created;


    /** qq */
    @ApiModelProperty(value = "qq")
    private String qq;

    /** qq */
    @ApiModelProperty(value = "昵称")
    private String qqName;

    /** qq */
    @ApiModelProperty(value = "头像")
    private String qqImg;

    /** 自动暂停时间 */
    @ApiModelProperty(value = "自动暂停时间")
    private String autoCloseTime;


}
