package com.blogs.model.article;

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
 * 对应表名: article_two_class
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_two_class")
public class ArticleTwoClass implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 一级id */
	@ApiModelProperty(value = "一级id")
	private Integer oneClassId;

	/** 标题 */
	@ApiModelProperty(value = "标题")
	private String name;

	/** 一级名称 */
	@ApiModelProperty(value = "一级名称")
	private String oneName;


	/** 字体图标 */
	@ApiModelProperty(value = "字体图标")
	private String icon;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

}