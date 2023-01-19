package com.blogs.model.test;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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
}
