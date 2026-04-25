package com.psychcenter.backend.common.exception.auth;

import com.psychcenter.backend.common.exception.base.BaseException;
import com.psychcenter.backend.common.exception.base.ErrorCode;

public class InvalidCredentialsException extends BaseException {

    public InvalidCredentialsException() {
        super(ErrorCode.INVALID_CREDENTIALS, "Invalid email or password");
    }
}