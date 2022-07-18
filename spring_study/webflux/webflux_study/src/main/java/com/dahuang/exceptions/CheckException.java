package com.dahuang.exceptions;

import lombok.Data;

@Data
public class CheckException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    /**
     *  出错字段的名字
     */
    private String fieldName;

    /**
     *   出错字段的值
     */
    private String fieldValue;

    public CheckException() {
    }

    public CheckException(String message) {
        super(message);
    }

    public CheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckException(Throwable cause) {
        super(cause);
    }

    public CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CheckException(String fieldName,String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
