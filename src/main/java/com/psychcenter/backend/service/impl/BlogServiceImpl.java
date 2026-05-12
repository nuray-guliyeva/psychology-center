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

    private final BlogRepository blogRepository;
    private final BlogMapper blogMapper;

    @Override
    public BlogResponseDto create(BlogPost post) {

        if (blogRepository.existsByTitle(post.getTitle())) {
            throw new RuntimeException("Blog with this title already exists");
        }

        post.setCreatedAt(LocalDateTime.now());
        return blogMapper.toDto(blogRepository.save(post));
    }

    @Override
    public Page<BlogResponseDto> getAll(int page, int size) {
        return blogRepository.findAll(PageRequest.of(page, size))
                .map(blogMapper::toDto);
    }

    @Override
    public BlogResponseDto getById(Long id) {
        return blogMapper.toDto(
                blogRepository.findById(id)
                        .orElseThrow(() -> new com.psychcenter.backend.common.exception.base.ResourceNotFoundException("Blog not found"))
        );
    }
}