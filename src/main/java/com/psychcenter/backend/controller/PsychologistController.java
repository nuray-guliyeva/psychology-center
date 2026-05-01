package com.psychcenter.backend.controller;

import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}