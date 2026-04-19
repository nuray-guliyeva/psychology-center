package com.psychcenter.backend.psychologist.service;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;

import java.util.List;

public interface PsychologistService {

    List<PsychologistResponseDto> getAll();

    PsychologistResponseDto getById(Long id);

    PsychologistResponseDto create(PsychologistRequestDto dto);
}