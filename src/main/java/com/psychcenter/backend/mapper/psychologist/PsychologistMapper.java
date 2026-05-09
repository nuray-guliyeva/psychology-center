package com.psychcenter.backend.mapper.psychologist;

import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.model.entity.psychologist.Psychologist;
import org.springframework.stereotype.Component;

@Component
public class PsychologistMapper {

    public Psychologist toEntity(PsychologistRequestDto dto) {
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

    public PsychologistResponseDto toDto(Psychologist p) {
        return PsychologistResponseDto.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .specialization(p.getSpecialization())
                .experienceYears(p.getExperienceYears())
                .bio(p.getBio())
                .education(p.getEducation())
                .certificates(p.getCertificates())
                .languages(p.getLanguages())
                .approach(p.getApproach())
                .rating(p.getRating())
                .build();
    }
}