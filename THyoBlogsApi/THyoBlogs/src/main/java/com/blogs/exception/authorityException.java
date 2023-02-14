package com.blogs.exception;

import com.blogs.vo.common.R;
import lombok.Data;

@Data
public class authorityException extends RuntimeException {
    private Integer code;
    private String message;

    public authorityException(Integer code, String message) {
        super(message);
        this.code = code;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
