package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import java.util.List;

public interface PsychologistService {

    PsychologistResponseDto create(PsychologistRequestDto dto);

    PsychologistResponseDto update(Long id, PsychologistRequestDto dto);

    void delete(Long id);

    List<PsychologistResponseDto> getAll();

    PsychologistResponseDto getById(Long id);

    List<PsychologistResponseDto> filter(String specialization, String language);

}