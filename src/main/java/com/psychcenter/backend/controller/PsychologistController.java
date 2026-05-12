package com.psychcenter.backend.controller;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/psychologists")
@RequiredArgsConstructor
public class PsychologistController {

    private final PsychologistService service;

    @GetMapping
    public ApiResponse<Page<PsychologistResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ApiResponse.success(service.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<PsychologistResponseDto> getById(@PathVariable Long id) {
        return ApiResponse.success(service.getById(id), "Psychologist fetched");
    }

    @GetMapping("/filter")
    public ApiResponse<List<PsychologistResponseDto>> filter(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String language
    ) {
        return ApiResponse.success(
                service.filter(specialization, language),
                "Filtered psychologists"
        );
    }
}