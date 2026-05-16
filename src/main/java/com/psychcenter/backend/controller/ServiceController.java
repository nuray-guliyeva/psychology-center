package com.psychcenter.backend.controller;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.request.ServiceCreateRequestDto;
import com.psychcenter.backend.dto.response.ServiceResponseDto;
import com.psychcenter.backend.service.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;

    @PostMapping
    public ApiResponse<ServiceResponseDto> create(
            @Valid
            @RequestBody ServiceCreateRequestDto dto
    ) {

        return ApiResponse.success(
                service.create(dto),
                "Service created"
        );
    }

    @GetMapping
    public ApiResponse<List<ServiceResponseDto>> getAll() {

        return ApiResponse.success(
                service.getAll(),
                "Services list"
        );
    }
}