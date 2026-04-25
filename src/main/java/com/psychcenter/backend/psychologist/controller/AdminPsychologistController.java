package com.psychcenter.backend.psychologist.controller;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/psychologists")
@RequiredArgsConstructor
public class AdminPsychologistController {

    private final PsychologistService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public PsychologistResponseDto create(@RequestBody PsychologistRequestDto dto) {
        return service.create(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public PsychologistResponseDto update(@PathVariable Long id, @RequestBody PsychologistRequestDto dto) {
        return service.update(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}