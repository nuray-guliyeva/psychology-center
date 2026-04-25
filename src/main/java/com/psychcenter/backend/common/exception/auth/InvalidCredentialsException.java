package com.psychcenter.backend.common.exception.auth;

import com.psychcenter.backend.common.exception.base.BaseException;

public class InvalidCredentialsException extends BaseException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}