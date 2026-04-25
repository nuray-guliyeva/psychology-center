package com.psychcenter.backend.auth.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequest {

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}