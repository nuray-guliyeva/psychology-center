package com.psychcenter.backend.psychologist.controller;

import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final PsychologistService service;

    @GetMapping
    public List<PsychologistResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PsychologistResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
}