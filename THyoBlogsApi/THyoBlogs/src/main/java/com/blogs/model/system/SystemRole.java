package com.blogs.model.system;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色绑定菜单列表
 * 对应表名: system_role
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_role")
public class SystemRole implements Serializable {

	/** 自增ID */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "自增ID")
	private Integer id;

	/** 角色id */
	@ApiModelProperty(value = "角色id")
	private Integer roleId;

	/** 菜单id */
	@ApiModelProperty(value = "菜单id")
	private Integer menusId;

}