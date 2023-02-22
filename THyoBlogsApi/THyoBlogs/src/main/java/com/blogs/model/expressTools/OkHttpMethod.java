package com.blogs.model.expressTools;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
public class OkHttpMethod {
    @ApiModelProperty(value = "方法")
    private String method;

    @ApiModelProperty(value = "请求地址")
    private String url;

    @ApiModelProperty(value = "携带参数")
    private Map<String,String> param;

    @ApiModelProperty(value = "header携带参数")
    private  Map<String,String> header;
}
