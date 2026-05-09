package com.psychcenter.backend.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psychcenter.backend.common.api.ApiResponse;
import jakarta.servlet.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        var body = ApiResponse.error(
                Map.of(
                        "status", 401,
                        "error", "Unauthorized",
                        "message", "Authentication required"
                )
        );

        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), body);
    }
}