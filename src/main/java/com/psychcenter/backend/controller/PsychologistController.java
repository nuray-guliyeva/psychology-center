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

    private final PsychologistService psychologistService;

    @GetMapping
    public ApiResponse<Page<PsychologistResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ApiResponse.success(psychologistService.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ApiResponse<PsychologistResponseDto> getById(@PathVariable Long id) {
        return ApiResponse.success(psychologistService.getById(id), "Psychologist fetched");
    }

    @GetMapping("/filter")
    public ApiResponse<List<PsychologistResponseDto>> filter(
            @RequestParam(required = false) String specialization,
            @RequestParam(required = false) String language
    ) {
        return ApiResponse.success(
                psychologistService.filter(specialization, language),
                "Filtered psychologists"
        );
    }

    @GetMapping("/recommend/{level}")
    public List<PsychologistResponseDto> recommend(@PathVariable String level) {
        return psychologistService.recommend(level);
    }
}