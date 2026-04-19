package com.psychcenter.backend.psychologist.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PsychologistResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String specialization;
    private Integer experienceYears;
}