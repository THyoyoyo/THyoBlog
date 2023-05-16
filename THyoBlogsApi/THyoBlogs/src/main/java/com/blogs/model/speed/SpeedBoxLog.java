package com.blogs.model.speed;

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
import org.springframework.lang.Nullable;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 数据库映射类
 * 对应表名: speed_box_log
 * @author 映射类自动生成器-LXY
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@TableName("speed_box_log")
public class SpeedBoxLog implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 自增id */
	@TableId(type = IdType.AUTO)
	@ApiModelProperty(value = "自增id")
	private Integer id;

	/** 归属记录id */
	@ApiModelProperty(value = "归属记录id")
	private Integer speedId;

	/** 开箱数据 */
	@ApiModelProperty(value = "开箱数据")
	private String json;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	private Date created;


	/** 列表数据 */
	@ApiModelProperty(value = "列表数据")
	@TableField(exist = false)
	private List<Map<String,Object>>  dataList;
}