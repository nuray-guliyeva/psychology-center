package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.request.BlogCreateRequestDto;
import com.psychcenter.backend.dto.response.BlogResponseDto;
import org.springframework.data.domain.Page;

public interface BlogService {

    BlogResponseDto create(BlogCreateRequestDto dto);

    Page<BlogResponseDto> getAll(int page, int size);

    BlogResponseDto getById(Long id);
}