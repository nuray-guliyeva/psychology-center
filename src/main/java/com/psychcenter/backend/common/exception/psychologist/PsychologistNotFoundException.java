package com.psychcenter.backend.common.exception.psychologist;

import com.psychcenter.backend.common.exception.base.BaseException;
import com.psychcenter.backend.common.exception.base.ErrorCode;
import org.springframework.http.HttpStatus;

public class PsychologistNotFoundException extends BaseException {

    public PsychologistNotFoundException(Long id) {
        super(
                ErrorCode.RESOURCE_NOT_FOUND,
                "Psychologist not found with id: " + id,
                HttpStatus.NOT_FOUND
        );
    }
}