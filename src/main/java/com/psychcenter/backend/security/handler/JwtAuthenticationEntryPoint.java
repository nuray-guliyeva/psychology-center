package com.psychcenter.backend.security.handler;

// Jackson class used for converting Java objects into JSON
import com.fasterxml.jackson.databind.ObjectMapper;

// Custom API response wrapper class
import com.psychcenter.backend.common.api.ApiResponse;

// HTTP request and response classes
import jakarta.servlet.http.*;

// Exception thrown when authentication fails
// Example: invalid token or missing token
import org.springframework.security.core.AuthenticationException;

// Interface used for handling unauthorized requests
import org.springframework.security.web.AuthenticationEntryPoint;

// Marks this class as Spring component
import org.springframework.stereotype.Component;

// Used for IOException handling
import java.io.IOException;

// Utility class for creating immutable maps
import java.util.Map;

// Registers this class as Spring bean
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // ObjectMapper converts Java objects into JSON
    private final ObjectMapper mapper = new ObjectMapper();

    // This method runs when unauthenticated user
    // tries to access protected endpoint
    @Override
    public void commence(

            // Current HTTP request
            HttpServletRequest request,

            // HTTP response object
            HttpServletResponse response,

            // Exception related to authentication failure
            AuthenticationException authException

    ) throws IOException {

        // Sets HTTP status code to 401 Unauthorized
        response.setStatus(
                HttpServletResponse.SC_UNAUTHORIZED
        );

        // Creates error response body
        var body = ApiResponse.error(

                // Creates map containing error details
                Map.of(

                        // HTTP status code
                        "status", 401,

                        // Error title
                        "error", "Unauthorized",

                        // Error message
                        "message", "Authentication required"
                )
        );

        // Sets response type to JSON
        response.setContentType("application/json");

        // Converts Java object into JSON
        // and writes it into HTTP response
        mapper.writeValue(
                response.getOutputStream(),
                body
        );
    }
}