package com.blogs.model.expressTools;


import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class Botany {
    @ApiModelProperty(value = "图片网络链接(二选一)")
    private String imgUrl;

    @ApiModelProperty(value = "图片base64(二选一)")
    private String imgBase64;



    @ApiModelProperty(value = "是否返回百科")
    private String baikeNum;
}
