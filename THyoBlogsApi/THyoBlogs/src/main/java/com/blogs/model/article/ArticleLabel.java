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

/**
 * 数据库映射类
 * 对应表名: article_label
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_label")
public class ArticleLabel implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 标签id */
	@ApiModelProperty(value = "标签id")
	private Integer labelId;

	/** 文章id */
	@ApiModelProperty(value = "文章id")
	private Integer articleId;

}