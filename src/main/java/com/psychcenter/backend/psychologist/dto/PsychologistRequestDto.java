package com.psychcenter.backend.psychologist.dto;

import lombok.Data;

@Data
public class PsychologistRequestDto {

    private String firstName;
    private String lastName;
    private String specialization;
    private Integer experienceYears;
    private String email;
    private String phone;
}