package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.response.BlogResponseDto;
import com.psychcenter.backend.mapper.BlogMapper;
import com.psychcenter.backend.model.entity.BlogPost;
import com.psychcenter.backend.repository.BlogRepository;
import com.psychcenter.backend.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository repository;
    private final BlogMapper mapper;

    @Override
    public BlogResponseDto create(BlogPost post) {
        post.setCreatedAt(LocalDateTime.now());
        return mapper.toDto(repository.save(post));
    }

    @Override
    public Page<BlogResponseDto> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(mapper::toDto);
    }

    @Override
    public BlogResponseDto getById(Long id) {
        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() -> new com.psychcenter.backend.common.exception.base.ResourceNotFoundException("Blog not found"))
        );
    }
}