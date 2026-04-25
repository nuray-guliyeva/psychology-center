package com.psychcenter.backend.common.exception.user;

import com.psychcenter.backend.common.exception.base.BaseException;
import com.psychcenter.backend.common.exception.base.ErrorCode;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String email) {
        super(ErrorCode.USER_ALREADY_EXISTS, "User already exists with email: " + email);
    }
}