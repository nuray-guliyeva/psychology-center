package com.psychcenter.backend.common.exception.booking;

import com.psychcenter.backend.common.exception.base.BaseException;
import com.psychcenter.backend.common.exception.base.ErrorCode;
import org.springframework.http.HttpStatus;

public class BookingConflictException extends BaseException {

    public BookingConflictException(String message) {
        super(ErrorCode.VALIDATION_ERROR, message, HttpStatus.CONFLICT);
    }
}