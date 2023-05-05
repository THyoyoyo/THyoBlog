package com.blogs.vo.speed;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SetAutoBoxInfoVo {

    @ApiModelProperty(value = "宝箱ID")
    private Integer boxId;

    @ApiModelProperty(value = "开启数量")
    private Integer openNum;

    @ApiModelProperty(value = "需要钥匙数量")
    private Integer keyNum1;

    @ApiModelProperty(value = "钥匙ID")
    private Integer keyId1;
}
