package com.psychcenter.backend.psychologist.dto;

import lombok.Data;

import java.util.List;

@Data
public class PsychologistRequestDto {

    private String firstName;
    private String lastName;
    private String specialization;
    private Integer experienceYears;
    private String email;
    private String phone;

    private String bio;
    private String education;
    private List<String> certificates;
    private List<String> languages;
    private String approach;
}