package com.isonah.usermanagement.exception;

/**
 * @author ielksseyer
 */
public class ExceptionError {

    private String code;

    private String message;

    private int status;

    public String getCode() {
        return code;
    }

    public ExceptionError setCode(String code) {
        this.code = code;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionError setMessage(String message) {
        this.message = message;
        return this;
    }

    public ExceptionError setStatus(int status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return code;
    }
}
