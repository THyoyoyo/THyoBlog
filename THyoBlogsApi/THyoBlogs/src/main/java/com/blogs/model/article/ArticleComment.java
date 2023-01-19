package com.blogs.model.article;

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
 * 数据库映射类
 * 对应表名: article_comment
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_comment")
public class ArticleComment implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 所属文章 */
	@ApiModelProperty(value = "所属文章")
	private Integer articleId;


	/** 所属文章 */
	@ApiModelProperty(value = "所属文章")
	@TableField(exist = false)
	private String title;

	/** 内容 */
	@ApiModelProperty(value = "内容")
	private String content;

	/** 创建人 */
	@ApiModelProperty(value = "创建人")
	private Integer userId;

	/** 创建人 */
	@ApiModelProperty(value = "创建昵称")
	private String userName;

	/** IP地址 */
	@ApiModelProperty(value = "IP地址")
	private String ip;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

	/** 上级评论Id */
	@ApiModelProperty(value = "上级评论Id")
	private Integer parentId;	/** 上级评论Id */



	@ApiModelProperty(value = "reply")
	@TableField(exist = false)
	private List<ArticleComment> replyList;


}