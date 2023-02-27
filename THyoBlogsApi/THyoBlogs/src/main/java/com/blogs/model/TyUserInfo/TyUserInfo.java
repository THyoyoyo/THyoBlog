package com.blogs.model.TyUserInfo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * 数据库映射类
 * 对应表名: ty_user_info
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("ty_user_info")
public class TyUserInfo implements Serializable {


	/** 自增id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "自增id")
	private Integer id;

	/** 腾游用户id */
	@ApiModelProperty(value = "腾游用户id")
	private String userId;

	/** 腾游用户token */
	@ApiModelProperty(value = "腾游用户token")
	private String token;

	/** 其他信息 */
	@ApiModelProperty(value = "其他信息")
	private String json;


	/** 其他信息 */
	@ApiModelProperty(value = "密码")
	private String password;

	/** 其他信息 */
	@ApiModelProperty(value = "状态: 0 关闭 1启动")
	private Integer state;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date created;

}