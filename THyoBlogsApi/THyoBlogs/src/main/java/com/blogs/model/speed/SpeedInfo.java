package com.blogs.model.speed;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 数据库映射类
 * 对应表名: speed_info
 *
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("speed_info")
public class SpeedInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增ID
     */
    @ApiModelProperty(value = "自增ID")
    @TableId(type = IdType.AUTO)
    private Integer id;


    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Integer speedUserId;

    /**
     * 账号信息URL
     */
    @ApiModelProperty(value = "账号信息URL")
    private String referer;

    @ApiModelProperty(value = "状态")
    private Integer state;

    @ApiModelProperty(value = "上次更新时间")
    private Date upTime;

	@Nullable
    private String serverName;
    private String areaName;
    private String appid;
    private String roleName;
    private String nickname;
    private String isMainRole;
	private String appOpenid;
	private String areaId;
	private String roleId;
	private String gameId;
	private String toUin;
	private String serverId;
	private String accessToken;

	@Nullable
	private String roleJob;
	private String token;
	private String uniqueRoleId;
	private String toOpenid;
	private String acctype;
	private String uin;
	private String roleLevel;
	private String userId;

}