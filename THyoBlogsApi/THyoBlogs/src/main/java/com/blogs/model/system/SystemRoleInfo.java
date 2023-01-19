package com.blogs.model.system;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 角色列表
 * 对应表名: system_role_info
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_role_info")
public class SystemRoleInfo implements Serializable {

	/** 自增ID */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "自增ID")
	private Integer id;

	/** 角色昵称 */
	@ApiModelProperty(value = "角色昵称")
	private String name;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

	/** 创建人 */
	@ApiModelProperty(value = "创建人")
	private Integer userId;

	/** 菜单权限列表 */
	@ApiModelProperty(value = "菜单权限列表Id")
	@TableField(exist = false)
	private List<Integer> menuIds;


	/** 菜单权限列表 */
	@ApiModelProperty(value = "菜单权限列表Id")
	@TableField(exist = false)
	private List<Map<String, Object>> menus;
}