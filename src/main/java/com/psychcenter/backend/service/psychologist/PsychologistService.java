package com.psychcenter.backend.service.psychologist;

import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PsychologistService {

    PsychologistResponseDto create(PsychologistRequestDto dto);

    PsychologistResponseDto update(Long id, PsychologistRequestDto dto);

    void delete(Long id);

    Page<PsychologistResponseDto> getAll(int page, int size);

    PsychologistResponseDto getById(Long id);

    List<PsychologistResponseDto> filter(String specialization, String language);

}