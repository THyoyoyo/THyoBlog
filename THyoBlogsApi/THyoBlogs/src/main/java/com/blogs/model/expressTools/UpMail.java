package com.blogs.model.expressTools;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class UpMail {
    @ApiModelProperty(value = "收件人")
    private String toEmail;
    @ApiModelProperty(value = "主题")
    private String ubject;
    @ApiModelProperty(value = "内容")
    private String text;
}
