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
 * 对应表名: article_home_recommend
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_home_recommend")
public class ArticleHomeRecommend implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 文章id */
	@ApiModelProperty(value = "文章id")
	private Integer articleId;

	/** 所属用户id */
	@ApiModelProperty(value = "所属用户id")
	private Integer userId;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

}