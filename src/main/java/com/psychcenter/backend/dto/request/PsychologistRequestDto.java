package com.psychcenter.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class PsychologistRequestDto {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50)
    private String firstName;


    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50)
    private String lastName;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotNull
    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experienceYears;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone is required")
    private String phone;

    @Size(max = 1000)
    private String bio;

    private String education;

    private List<String> certificates;

    @NotEmpty(message = "At least one language required")
    private List<String> languages;

    private String approach;
}