package com.blogs.model.test;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName("test")
@Data
public class Test  implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private  Integer id;

    @ApiModelProperty(value = "姓名")
    private  String name;

    @ApiModelProperty(value = "年龄")
    private  Integer age;

    @ApiModelProperty(value = "数量")
    private  Integer num;

    @ApiModelProperty(value = "时间")
    private Date time;
}
