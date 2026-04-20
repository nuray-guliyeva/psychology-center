package com.psychcenter.backend.psychologist.controller;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psychologists")
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

    @PostMapping
    public PsychologistResponseDto create(@RequestBody PsychologistRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public PsychologistResponseDto update(
            @PathVariable Long id,
            @RequestBody PsychologistRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}