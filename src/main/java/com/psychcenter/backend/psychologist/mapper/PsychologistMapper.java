package com.psychcenter.backend.psychologist.mapper;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.entity.Psychologist;

public class PsychologistMapper {

    public static Psychologist toEntity(PsychologistRequestDto dto) {
        Psychologist p = new Psychologist();
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setSpecialization(dto.getSpecialization());
        p.setExperienceYears(dto.getExperienceYears());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        return p;
    }

    public static PsychologistResponseDto toDto(Psychologist entity) {
        return PsychologistResponseDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .specialization(entity.getSpecialization())
                .experienceYears(entity.getExperienceYears())
                .build();
    }
}