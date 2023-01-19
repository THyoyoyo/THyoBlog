package com.blogs.model.article;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.blogs.vo.article.ArticleTwoClassVo;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;

/**
 * 数据库映射类
 * 对应表名: article_one_class
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_one_class")
public class ArticleOneClass implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 所属用户id */
	@ApiModelProperty(value = "所属用户id")
	private Integer userId;


	/** 标题 */
	@ApiModelProperty(value = "标题")
	private String name;

	/** 字体图标 */
	@ApiModelProperty(value = "字体图标")
	private String icon;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;


	/** 类别二级列表 */
	@ApiModelProperty(value = "类别二级id")
	@TableField(exist = false)
	private List<ArticleTwoClass> list;



	/** 用户姓名 */
	@ApiModelProperty(value = "用户姓名")
	@TableField(exist = false)
	private String userName;

}