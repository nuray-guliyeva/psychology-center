package com.psychcenter.backend.common.exception.global;

// Imports custom API response wrapper class
import com.psychcenter.backend.common.api.ApiResponse;

// Imports custom base exception class
import com.psychcenter.backend.common.exception.base.BaseException;

// Imports enum/class that stores application error codes
import com.psychcenter.backend.common.exception.base.ErrorCode;

// Gives access to HTTP request information
import jakarta.servlet.http.HttpServletRequest;

// Spring HTTP status codes (200, 400, 500, etc.)
import org.springframework.http.HttpStatus;

// Used to return full HTTP response with status and body
import org.springframework.http.ResponseEntity;

// Exception thrown when validation annotations fail
// Example: @NotBlank, @Size, @Email
import org.springframework.web.bind.MethodArgumentNotValidException;

// Marks methods that handle exceptions
import org.springframework.web.bind.annotation.ExceptionHandler;

// Makes this class global exception handler for all controllers
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Used for storing current date and time
import java.time.LocalDateTime;

// HashMap implementation for storing key-value pairs
import java.util.HashMap;

// Interface for key-value collection
import java.util.Map;

// @RestControllerAdvice means:
// this class globally catches exceptions from all controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles all exceptions that extend BaseException
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBaseException(

            // Caught exception object
            BaseException ex,

            // Current HTTP request object
            HttpServletRequest request
    ) {

        // Returns HTTP response with custom status and body
        return ResponseEntity

                // Sets HTTP status from exception
                .status(ex.getStatus())

                // Sets response body
                .body(ApiResponse.error(

                        // Creates ErrorResponse object using builder
                        ErrorResponse.builder()

                                // Adds current timestamp
                                .timestamp(LocalDateTime.now())

                                // Adds HTTP status code
                                .status(ex.getStatus().value())

                                // Adds custom application error code
                                .errorCode(ex.getErrorCode().getCode())

                                // Adds error name
                                .error(ex.getErrorCode().name())

                                // Adds exception message
                                .message(ex.getMessage())

                                // Adds request path (endpoint URL)
                                .path(request.getRequestURI())

                                // Builds ErrorResponse object
                                .build()
                ));
    }

    // Handles validation exceptions
    // Example: invalid request body fields
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidation(

            // Validation exception object
            MethodArgumentNotValidException ex,

            // Current HTTP request
            HttpServletRequest request
    ) {

        // Map for storing field errors
        // Example:
        // "email" -> "Email must not be blank"
        Map<String, String> fieldErrors = new HashMap<>();

        // Loops through all validation errors
        ex.getBindingResult().getFieldErrors().forEach(error ->

                // Adds field name and error message into map
                fieldErrors.put(
                        error.getField(),
                        error.getDefaultMessage()
                )
        );

        // Returns HTTP 400 response
        return ResponseEntity

                // Sets HTTP status BAD_REQUEST (400)
                .status(HttpStatus.BAD_REQUEST)

                // Sets response body
                .body(ApiResponse.error(

                        // Builds detailed validation error response
                        ErrorResponse.builder()

                                // Current date and time
                                .timestamp(LocalDateTime.now())

                                // HTTP status code
                                .status(400)

                                // Custom validation error code
                                .errorCode(ErrorCode.VALIDATION_ERROR.getCode())

                                // Error title
                                .error("Validation Error")

                                // General message
                                .message("Validation failed")

                                // Detailed field errors
                                .errors(fieldErrors)

                                // Endpoint path
                                .path(request.getRequestURI())

                                // Builds object
                                .build()
                ));
    }

    // Handles all other unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneric(

            // Exception object
            Exception ex,

            // Current request
            HttpServletRequest request
    ) {

        // Returns HTTP 500 Internal Server Error
        return ResponseEntity

                // Sets status code 500
                .status(HttpStatus.INTERNAL_SERVER_ERROR)

                // Sets response body
                .body(ApiResponse.error(

                        // Builds error response object
                        ErrorResponse.builder()

                                // Current timestamp
                                .timestamp(LocalDateTime.now())

                                // Status code
                                .status(500)

                                // Generic internal error code
                                .errorCode(9999)

                                // Error title
                                .error("Internal Server Error")

                                // Actual exception message
                                .message(ex.getMessage())

                                // Request URL path
                                .path(request.getRequestURI())

                                // Builds final object
                                .build()
                ));
    }
}