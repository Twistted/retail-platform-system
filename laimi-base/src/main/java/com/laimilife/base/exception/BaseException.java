package com.laimilife.base.exception;

public class BaseException extends RuntimeException {

    private String code; // 错误代码
    
    private String desc; // 错误描述

    public BaseException(String code, String desc) {
        this.code=code;
        this.desc=desc;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
