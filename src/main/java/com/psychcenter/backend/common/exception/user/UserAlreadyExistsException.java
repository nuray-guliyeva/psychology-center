package com.psychcenter.backend.common.exception.user;

import com.psychcenter.backend.common.exception.base.BaseException;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}