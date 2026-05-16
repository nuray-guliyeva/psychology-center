package com.psychcenter.backend.controller;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.request.BlogCreateRequestDto;
import com.psychcenter.backend.dto.response.BlogResponseDto;
import com.psychcenter.backend.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService service;

    @PostMapping
    public ApiResponse<BlogResponseDto> create(
            @Valid
            @RequestBody BlogCreateRequestDto dto
    ) {

        return ApiResponse.success(
                service.create(dto),
                "Blog created"
        );
    }

    @GetMapping
    public ApiResponse<Page<BlogResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        return ApiResponse.success(
                service.getAll(page, size),
                "Blog list"
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<BlogResponseDto> getById(
            @PathVariable Long id
    ) {

        return ApiResponse.success(
                service.getById(id),
                "Blog fetched"
        );
    }
}