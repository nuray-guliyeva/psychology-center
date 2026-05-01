package com.psychcenter.backend.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PsychologistResponseDto {

    private Long id;

    private String firstName;
    private String lastName;

    private String specialization;
    private Integer experienceYears;

    private String bio;
    private String education;

    private List<String> certificates;
    private List<String> languages;

    private String approach;
    private Double rating;
}