package com.blogs.model.article;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 数据库映射类
 * 对应表名: article
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 标题 */
	@ApiModelProperty(value = "标题")
	private String title;

	/** 创建用户 */
	@ApiModelProperty(value = "创建用户")
	private Integer userId;

	/** 创建用户 */
	@ApiModelProperty(value = "创建用户")
	@TableField(exist = false)
	private String userName;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

	/** 查看人数 */
	@ApiModelProperty(value = "查看人数")
	private Integer viewNum;

	/** 简介 */
	@ApiModelProperty(value = "简介")
	private String brief;

	/** 一级id */
	@ApiModelProperty(value = "一级id")
	private Integer classOneId;

	/** 文章内容 */
	@ApiModelProperty(value = "文章内容")
	private String content;


	/** 文章内容 */
	@ApiModelProperty(value = "展示图片")
	private String preview;

	/** 二级id */
	@ApiModelProperty(value = "二级id")
	private Integer classTwoId;

	/** 二级id */
	@ApiModelProperty(value = "是否首页推荐")
	@TableField(exist = false)
	private Integer homeCommend;


	/** 二级id */
	@ApiModelProperty(value = "标签id")
	@TableField(exist = false)
	private List<Integer> labels;


	/** 二级id */
	@ApiModelProperty(value = "标签（不传）")
	@TableField(exist = false)
	private List<ArticleLabelInfo> labelName;

	/** 一级类目名称 */
	@ApiModelProperty(value = "一级类目名称（不传）")
	@TableField(exist = false)
	private String oneName;


	/** 二级类目名称 */
	@ApiModelProperty(value = "二级类目名称（不传）")
	@TableField(exist = false)
	private String twoName;


	/** 最新评论 */
	@ApiModelProperty(value = "最新评论（不传）")
	@TableField(exist = false)
	private ArticleComment  latestComments;

	/** 评论数量 */
	@ApiModelProperty(value = "评论数量（不传）")
	@TableField(exist = false)
	private Integer articleCommentNum;
}