package com.psychcenter.backend.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class PsychologistRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String specialization;

    @NotNull
    @Min(0)
    private Integer experienceYears;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @Size(max = 2000)
    private String bio;

    private String education;

    private List<String> certificates;

    private List<String> languages;

    private String approach;
}