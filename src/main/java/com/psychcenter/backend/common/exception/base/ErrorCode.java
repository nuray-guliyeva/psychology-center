package com.psychcenter.backend.common.exception.base;

public enum ErrorCode {

    USER_ALREADY_EXISTS(1001),
    USER_NOT_FOUND(1002),
    INVALID_CREDENTIALS(1003),
    RESOURCE_NOT_FOUND(1004),
    VALIDATION_ERROR(1005);

    private final int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}