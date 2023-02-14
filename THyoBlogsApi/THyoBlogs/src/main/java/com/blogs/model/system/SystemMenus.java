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

/**
 * 菜单列表
 * 对应表名: system_menus
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_menus")
public class SystemMenus implements Serializable {


	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "自增ID")
	private Integer id;

	/** 菜单名称 */
	@ApiModelProperty(value = "菜单名称")
	private String name;

	/** 菜单图标 */
	@ApiModelProperty(value = "菜单图标")
	private String icon;

	/** 菜单前端路径 */
	@ApiModelProperty(value = "菜单前端路径")
	private String router;

	/** 路由名称 */
	@ApiModelProperty(value = "路由名称")
	private String routerName;

	/** 前端页面文件地址 */
	@ApiModelProperty(value = "前端页面文件地址")
	private String fileAddress;

	/** 上级菜单id */
	@ApiModelProperty(value = "上级菜单id")
	private Integer parentId;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

	/** 创建人 */
	@ApiModelProperty(value = "创建人")
	private Integer userId;

	/** 排序 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/** 类型（1:菜单,2:按钮） */
	@ApiModelProperty(value = "类型（1:菜单,2:按钮）")
	private Integer type;

	/** 子菜单 */
	@ApiModelProperty(value = "子菜单")
	@TableField(exist = false)
	private List<SystemMenus> childs;

}