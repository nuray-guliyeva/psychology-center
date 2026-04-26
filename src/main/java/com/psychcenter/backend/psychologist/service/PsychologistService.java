package com.psychcenter.backend.psychologist.service;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PsychologistService {

    Page<PsychologistResponseDto> getAll(
            String specialization,
            String language,
            Integer minExperience,
            Pageable pageable
    );

    PsychologistResponseDto getById(Long id);

    PsychologistResponseDto create(PsychologistRequestDto dto);

    PsychologistResponseDto update(Long id, PsychologistRequestDto dto);

    void delete(Long id);
}