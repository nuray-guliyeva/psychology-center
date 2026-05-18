package com.psychcenter.backend.common.exception.base;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus status;

    public BaseException(ErrorCode errorCode, String message, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = HttpStatus.BAD_REQUEST;
    }
}