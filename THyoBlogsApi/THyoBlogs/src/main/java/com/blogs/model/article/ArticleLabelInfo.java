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

/**
 * 数据库映射类
 * 对应表名: article_label_info
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_label_info")
public class ArticleLabelInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "id")
	private Integer id;

	/** 标题 */
	@ApiModelProperty(value = "标题")
	private String name;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date creationTime;

	/** 所属用户 */
	@ApiModelProperty(value = "所属用户")
	private Integer userId;
	/** 所属用户 */

	@ApiModelProperty(value = "所属用户")
	@TableField(exist = false)
	private String userName;

}