package com.blogs.model.webConfig;

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
 * 对应表名: web_config
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("web_config")
public class WebConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 标题 */
	@ApiModelProperty(value = "标题")
	private String titlte;

	/** logo */
	@ApiModelProperty(value = "logo")
	private String logo;

	/** 简介 */
	@ApiModelProperty(value = "简介")
	private String brief;

	/** 所属用户Id */
	@ApiModelProperty(value = "所属用户Id")
	private Integer userId;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

}