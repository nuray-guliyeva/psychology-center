package com.psychcenter.backend.security.filter;

// Imports custom JWT service
// Used for extracting and validating JWT tokens
import com.psychcenter.backend.service.security.JwtService;

// Custom UserDetailsService implementation
// Used for loading user information from database
import com.psychcenter.backend.service.security.impl.UserDetailsServiceImpl;

// Exception thrown when JWT token is invalid
import io.jsonwebtoken.JwtException;

// Lombok annotation for automatic constructor generation
import lombok.RequiredArgsConstructor;

// Authentication object used by Spring Security
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

// Stores authentication information for current request
import org.springframework.security.core.context.SecurityContextHolder;

// Adds additional authentication details from request
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

// Marks this class as Spring component
import org.springframework.stereotype.Component;

// Filter that runs once for every request
import org.springframework.web.filter.OncePerRequestFilter;

// Servlet imports
import jakarta.servlet.*;

// HTTP request and response classes
import jakarta.servlet.http.*;

// Used for IOException
import java.io.IOException;

// Registers this class as Spring bean
@Component

// Creates constructor automatically for final fields
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    // Service for JWT operations
    private final JwtService jwtService;

    // Service for loading user details
    private final UserDetailsServiceImpl userDetailsService;

    // This method runs for every HTTP request
    @Override
    protected void doFilterInternal(

            // Incoming HTTP request
            HttpServletRequest request,

            // HTTP response
            HttpServletResponse response,

            // Used to continue filter chain
            FilterChain filterChain

    ) throws ServletException, IOException {

        // Gets Authorization header from request
        // Example:
        // Authorization: Bearer eyJhbGci...
        String header = request.getHeader("Authorization");

        // Checks if header is missing
        // OR does not start with "Bearer "
        if (header == null || !header.startsWith("Bearer ")) {

            // Continues request without authentication
            filterChain.doFilter(request, response);

            // Stops method execution
            return;
        }

        // Removes "Bearer " part from token
        // Example:
        // "Bearer abc123" -> "abc123"
        String token = header.substring(7);

        try {

            // Extracts username from JWT token
            String username = jwtService.extractUsername(token);

            // Checks:
            // 1. username exists
            // 2. user is not already authenticated
            if (
                    username != null &&
                            SecurityContextHolder
                                    .getContext()
                                    .getAuthentication() == null
            ) {

                // Loads user details from database
                var userDetails =
                        userDetailsService
                                .loadUserByUsername(username);

                // Validates JWT token
                if (
                        jwtService.isTokenValid(
                                token,
                                userDetails.getUsername()
                        )
                ) {

                    // Creates authentication object
                    var authToken =
                            new UsernamePasswordAuthenticationToken(

                                    // Authenticated user
                                    userDetails,

                                    // Password is null because JWT is used
                                    null,

                                    // User roles/authorities
                                    userDetails.getAuthorities()
                            );

                    // Adds request details to authentication object
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    // Saves authentication into SecurityContext
                    // Now Spring Security considers user authenticated
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);
                }
            }

        } catch (JwtException ex) {

            // Clears authentication data if token is invalid
            SecurityContextHolder.clearContext();

            // Returns HTTP 401 Unauthorized
            response.setStatus(
                    HttpServletResponse.SC_UNAUTHORIZED
            );

            // Sets response type as JSON
            response.setContentType("application/json");

            // Sends JSON error response
            response.getWriter().write("""
            {
              \"success\": false,
              \"message\": \"Invalid or expired JWT token\"
            }
            """);

            // Stops request processing
            return;
        }

        // Continues request to next filter/controller
        filterChain.doFilter(request, response);
    }
}