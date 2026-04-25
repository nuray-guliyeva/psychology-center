package com.psychcenter.backend.psychologist.controller;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
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
    public ApiResponse<List<PsychologistResponseDto>> getAll() {
        return ApiResponse.success(
                service.getAll(),
                "Psychologists fetched successfully"
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<PsychologistResponseDto> getById(@PathVariable Long id) {
        return ApiResponse.success(
                service.getById(id),
                "Psychologist fetched successfully"
        );
    }

    @PostMapping
    public ApiResponse<PsychologistResponseDto> create(@RequestBody PsychologistRequestDto dto) {
        return ApiResponse.success(
                service.create(dto),
                "Psychologist created successfully"
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<PsychologistResponseDto> update(
            @PathVariable Long id,
            @RequestBody PsychologistRequestDto dto
    ) {
        return ApiResponse.success(
                service.update(id, dto),
                "Psychologist updated successfully"
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success(null, "Psychologist deleted successfully");
    }
}