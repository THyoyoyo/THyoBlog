package com.blogs.model.system;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;

/**
 * 数据库映射类
 * 对应表名: system_imgs
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("system_imgs")
public class SystemImgs implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "ID")
	private Integer id;

	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称")
	private String name;

	/**
	 * 地址
	 */
	@ApiModelProperty(value = "地址")
	private String url;

	/**
	 * 文件类型
	 */
	@ApiModelProperty(value = "文件类型")
	private String type;

	/**
	 * 文件类型
	 */
	@ApiModelProperty(value = "文件类型")
	private Date creationTime;

}