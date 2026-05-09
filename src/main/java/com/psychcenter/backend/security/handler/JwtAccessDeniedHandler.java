package com.psychcenter.backend.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psychcenter.backend.common.api.ApiResponse;
import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        var body = ApiResponse.error(
                Map.of(
                        "status", 403,
                        "error", "Forbidden",
                        "message", "Access denied"
                )
        );

        response.setContentType("application/json");
        mapper.writeValue(response.getOutputStream(), body);
    }
}