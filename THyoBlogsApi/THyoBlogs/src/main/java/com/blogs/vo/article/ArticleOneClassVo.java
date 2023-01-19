package com.blogs.vo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.blogs.model.article.ArticleTwoClass;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
public class ArticleOneClassVo implements Serializable {

	/** id */
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 标题 */
	@ApiModelProperty(value = "标题")
	private String name;

	/** 类别二级列表 */
	@ApiModelProperty(value = "类别二级id")
	private List<ArticleTwoClass> list;

}