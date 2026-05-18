package com.psychcenter.backend.common.api;

// Imports Lombok annotation that automatically creates:
// getters, setters, toString(), equals(), and hashCode()
import lombok.Builder;

// Imports Lombok annotation that automatically generates
// getters, setters, toString(), equals(), and hashCode()
import lombok.Data;

// @Data is a Lombok annotation.
// It automatically creates getters/setters and other useful methods.
@Data

// @Builder allows us to create objects using Builder pattern.
// Example:
// ApiResponse.builder().success(true).build();
@Builder
public class ApiResponse<T> {

    // Indicates whether request was successful or not
    private boolean success;

    // Generic field that can store any type of response data
    // Example: User object, List, String, etc.
    private T data;

    // Stores success or informational message
    private String message;

    // Stores error details if something goes wrong
    private Object error;

    // Static method for creating successful response with data and message
    public static <T> ApiResponse<T> success(T data, String message) {

        // Creates ApiResponse object using builder pattern
        return ApiResponse.<T>builder()

                // Sets success to true
                .success(true)

                // Sets returned data
                .data(data)

                // Sets response message
                .message(message)

                // Builds and returns final object
                .build();
    }

    // Overloaded success method with default message "Success"
    public static <T> ApiResponse<T> success(T data) {

        // Calls previous success method
        return success(data, "Success");
    }

    // Static method for creating error response
    public static ApiResponse<?> error(Object error) {

        // Creates ApiResponse object using builder
        return ApiResponse.builder()

                // Sets success to false because request failed
                .success(false)

                // Stores error object/details
                .error(error)

                // Builds and returns final object
                .build();
    }
}