package com.psychcenter.backend.psychologist.controller;

import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final PsychologistService service;

    @GetMapping
    public Page<PsychologistResponseDto> getAll(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minExperience,
            Pageable pageable
    ) {
        return service.getAll(specialization, language, minExperience, pageable);
    }

    @GetMapping("/{id}")
    public PsychologistResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
}