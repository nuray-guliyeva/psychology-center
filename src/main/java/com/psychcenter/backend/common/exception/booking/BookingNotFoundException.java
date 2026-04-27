package com.psychcenter.backend.common.exception.booking;

import com.psychcenter.backend.common.exception.base.BaseException;
import com.psychcenter.backend.common.exception.base.ErrorCode;
import org.springframework.http.HttpStatus;

public class BookingNotFoundException extends BaseException {

    public BookingNotFoundException(String message) {
        super(ErrorCode.RESOURCE_NOT_FOUND, message, HttpStatus.NOT_FOUND);
    }
}