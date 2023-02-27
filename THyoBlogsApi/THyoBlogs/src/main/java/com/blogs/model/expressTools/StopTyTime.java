package com.blogs.model.expressTools;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class StopTyTime {

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "状态 1:暂停,0:恢复")
    private Integer status = 1;
}
