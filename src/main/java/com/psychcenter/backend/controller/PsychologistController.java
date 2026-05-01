package com.psychcenter.backend.controller;

import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final PsychologistService service;

    @GetMapping
    public List<PsychologistResponseDto> getAll() {        return service.getAll();
    }

    @GetMapping("/{id}")
    public PsychologistResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/filter")
    public List<PsychologistResponseDto> filter(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String language
    ) {
        return service.filter(specialization, language);
    }
}