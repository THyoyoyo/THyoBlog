package com.blogs.model.system;

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
	@ApiModelProperty(value = "id")
	@TableId(type = IdType.AUTO)
	private Integer id;

	/** 网站标题 */
	@ApiModelProperty(value = "网站标题")
	private String titlte;

	/** 个人头像 */
	@ApiModelProperty(value = "个人头像")
	private String userImg;

	/** 关于简介 */
	@ApiModelProperty(value = "关于简介")
	private String brief;

	/** 个人简介 */
	@ApiModelProperty(value = "个人简介")
	private String signature;

	/** 个人昵称 */
	@ApiModelProperty(value = "个人昵称")
	private String userName;

	/** 微信 */
	@ApiModelProperty(value = "微信")
	private String wechat;

	/** github */
	@ApiModelProperty(value = "github")
	private String github;

	/** 微博 */
	@ApiModelProperty(value = "微博")
	private String weibo;

	/** QQ */
	@ApiModelProperty(value = "QQ")
	private String qq;

	/** 首页背景图 */
	@ApiModelProperty(value = "首页背景图")
	private String indexBack;

	/** 归档背景图 */
	@ApiModelProperty(value = "归档背景图")
	private String archiveBack;

	/** 关于我背景图 */
	@ApiModelProperty(value = "关于我背景图")
	private String aboutmeBack;

	/** 留言板背景图 */
	@ApiModelProperty(value = "留言板背景图")
	private String messageboardBack;

}