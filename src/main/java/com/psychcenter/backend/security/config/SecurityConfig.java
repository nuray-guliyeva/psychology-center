package com.psychcenter.backend.security.config;

// Imports custom JWT filter
// This filter checks JWT token in every request
import com.psychcenter.backend.security.filter.JwtFilter;

// Handles unauthorized access (401 Unauthorized)
import com.psychcenter.backend.security.handler.JwtAuthenticationEntryPoint;

// Handles forbidden access (403 Forbidden)
import com.psychcenter.backend.security.handler.JwtAccessDeniedHandler;

// Lombok annotation that creates constructor
// for all final fields automatically
import lombok.RequiredArgsConstructor;

// Spring annotations for configuration and beans
import org.springframework.context.annotation.*;

// Used for checking HTTP methods like GET, POST
import org.springframework.http.HttpMethod;

// Enables method-level security annotations
// Example: @PreAuthorize
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

// Defines session management policy
import org.springframework.security.config.http.SessionCreationPolicy;

// Main Spring Security configuration object
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

// Imports security classes
import org.springframework.security.web.*;

// Default Spring login filter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// BCrypt password encoder
import org.springframework.security.crypto.bcrypt.*;

// Interface for password encoding
import org.springframework.security.crypto.password.PasswordEncoder;

// Marks this class as Spring configuration
@Configuration

// Automatically creates constructor for final fields
@RequiredArgsConstructor

// Enables method security annotations
@EnableMethodSecurity
public class SecurityConfig {

    // Custom JWT filter
    private final JwtFilter jwtFilter;

    // Handles authentication errors
    private final JwtAuthenticationEntryPoint entryPoint;

    // Handles access denied errors
    private final JwtAccessDeniedHandler accessDeniedHandler;

    // Creates SecurityFilterChain bean
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Starts security configuration
        http

                // Disables CSRF protection
                // Usually disabled in REST APIs using JWT
                .csrf(csrf -> csrf.disable())

                // Configures session management
                .sessionManagement(sm ->

                        // Makes application stateless
                        // Server will not store sessions
                        sm.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                // Configures exception handling
                .exceptionHandling(ex -> ex

                        // Called when user is not authenticated
                        .authenticationEntryPoint(entryPoint)

                        // Called when user has no permission
                        .accessDeniedHandler(accessDeniedHandler)
                )

                // Configures endpoint authorization rules
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints accessible without token
                        .requestMatchers(
                                "/api/v1/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Allows everyone to GET blog data
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/blog/**"
                        )
                        .permitAll()

                        // Allows everyone to GET psychologists data
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/v1/psychologists/**"
                        )
                        .permitAll()

                        // Only ADMIN can access admin endpoints
                        .requestMatchers("/api/v1/admin/**")
                        .hasRole("ADMIN")

                        // ADMIN and PSYCHOLOGIST can create blog posts
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/v1/blog/**"
                        )
                        .hasAnyRole("ADMIN", "PSYCHOLOGIST")

                        // ADMIN and PSYCHOLOGIST can manage psychologists endpoints
                        .requestMatchers("/api/v1/psychologists/**")
                        .hasAnyRole("ADMIN", "PSYCHOLOGIST")

                        // All other endpoints require authentication
                        .anyRequest()
                        .authenticated()
                )

                // Adds JWT filter before default username/password filter
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        // Builds and returns security configuration
        return http.build();
    }

    // Creates PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {

        // BCrypt hashes passwords securely
        return new BCryptPasswordEncoder();
    }
}