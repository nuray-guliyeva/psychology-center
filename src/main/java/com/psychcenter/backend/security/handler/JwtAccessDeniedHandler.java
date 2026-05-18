package com.psychcenter.backend.security.handler;

// Jackson class used for converting Java objects to JSON
import com.fasterxml.jackson.databind.ObjectMapper;

// Custom API response wrapper
import com.psychcenter.backend.common.api.ApiResponse;

// HTTP request and response classes
import jakarta.servlet.http.*;

// Exception thrown when user has no permission
// to access a resource
import org.springframework.security.access.AccessDeniedException;

// Interface for handling access denied errors
import org.springframework.security.web.access.AccessDeniedHandler;

// Marks this class as Spring component
import org.springframework.stereotype.Component;

// Used for input/output exceptions
import java.io.IOException;

// Utility class for creating immutable maps
import java.util.Map;

// Registers this class as Spring bean
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    // ObjectMapper converts Java objects into JSON
    private final ObjectMapper mapper = new ObjectMapper();

    // This method runs when authenticated user
    // tries to access forbidden resource
    @Override
    public void handle(

            // Current HTTP request
            HttpServletRequest request,

            // HTTP response object
            HttpServletResponse response,

            // Exception containing access denied details
            AccessDeniedException accessDeniedException

    ) throws IOException {

        // Sets HTTP status code to 403 Forbidden
        response.setStatus(
                HttpServletResponse.SC_FORBIDDEN
        );

        // Creates error response body
        var body = ApiResponse.error(

                // Creates map with error details
                Map.of(

                        // HTTP status code
                        "status", 403,

                        // Error title
                        "error", "Forbidden",

                        // Error message
                        "message", "Access denied"
                )
        );

        // Sets response content type to JSON
        response.setContentType("application/json");

        // Converts Java object to JSON
        // and writes it into response output stream
        mapper.writeValue(
                response.getOutputStream(),
                body
        );
    }
}