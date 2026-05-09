package com.psychcenter.backend.controller.service;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.response.ServiceResponseDto;
import com.psychcenter.backend.model.entity.service.ServiceEntity;
import com.psychcenter.backend.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceService service;

    @PostMapping
    public ApiResponse<ServiceResponseDto> create(@RequestBody ServiceEntity s) {
        return ApiResponse.success(service.create(s), "Service created");
    }

    @GetMapping
    public ApiResponse<List<ServiceResponseDto>> getAll() {
        return ApiResponse.success(service.getAll(), "Services list");
    }
}