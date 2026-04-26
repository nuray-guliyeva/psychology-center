package com.psychcenter.backend.psychologist.mapper;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.entity.Psychologist;

public class PsychologistMapper {

    public static Psychologist toEntity(PsychologistRequestDto dto) {
        return Psychologist.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .specialization(dto.getSpecialization())
                .experienceYears(dto.getExperienceYears())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .bio(dto.getBio())
                .education(dto.getEducation())
                .certificates(dto.getCertificates())
                .languages(dto.getLanguages())
                .approach(dto.getApproach())
                .build();
    }

    public static PsychologistResponseDto toDto(Psychologist entity) {
        return PsychologistResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .specialization(entity.getSpecialization())
                .experienceYears(entity.getExperienceYears())
                .bio(entity.getBio())
                .education(entity.getEducation())
                .certificates(entity.getCertificates())
                .languages(entity.getLanguages())
                .approach(entity.getApproach())
                .rating(entity.getRating())
                .build();
    }
}