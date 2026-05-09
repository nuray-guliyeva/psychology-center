package com.psychcenter.backend.mapper.vacancy;

import com.psychcenter.backend.dto.response.VacancyResponseDto;
import com.psychcenter.backend.model.entity.vacancy.Vacancy;
import org.springframework.stereotype.Component;

@Component
public class VacancyMapper {

    public VacancyResponseDto toDto(Vacancy v) {
        return VacancyResponseDto.builder()
                .id(v.getId())
                .position(v.getPosition())
                .location(v.getLocation())
                .type(v.getType())
                .description(v.getDescription())
                .build();
    }
}