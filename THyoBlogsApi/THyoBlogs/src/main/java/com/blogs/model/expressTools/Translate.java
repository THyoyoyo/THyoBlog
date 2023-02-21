package com.blogs.model.expressTools;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Translate {
    @ApiModelProperty(value = "翻译文本")
    private String text;
    @ApiModelProperty(value = "源语言")
    private String from="auto";
    @ApiModelProperty(value = "目标语言")
    private String to="en";
    @ApiModelProperty(value = "翻译结果")
    private String trans_result;
}
