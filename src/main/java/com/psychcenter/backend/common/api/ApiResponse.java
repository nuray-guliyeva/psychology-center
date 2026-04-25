package com.psychcenter.backend.common.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String message;
    private Object error;

    public static <T> ApiResponse<T> success(T data, String message) {
        return ApiResponse.<T>builder()
                .success(true)
                .data(data)
                .message(message)
                .build();
    }

    public static ApiResponse<?> error(Object error) {
        return ApiResponse.builder()
                .success(false)
                .error(error)
                .build();
    }
}