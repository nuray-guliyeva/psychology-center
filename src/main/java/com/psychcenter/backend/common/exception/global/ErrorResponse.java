package com.psychcenter.backend.common.exception.global;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private int errorCode;
    private String error;
    private String message;
    private String path;
    private Map<String, String> errors;
}