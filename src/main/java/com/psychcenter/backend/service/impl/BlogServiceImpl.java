package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.common.exception.blog.BlogAlreadyExistsException;
import com.psychcenter.backend.dto.request.BlogCreateRequestDto;
import com.psychcenter.backend.dto.response.BlogResponseDto;
import com.psychcenter.backend.mapper.BlogMapper;
import com.psychcenter.backend.model.entity.BlogPost;
import com.psychcenter.backend.model.entity.Psychologist;
import com.psychcenter.backend.repository.BlogRepository;
import com.psychcenter.backend.repository.PsychologistRepository;
import com.psychcenter.backend.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final PsychologistRepository psychologistRepository;
    private final BlogMapper blogMapper;

    @Override
    @Transactional
    public BlogResponseDto create(BlogCreateRequestDto dto) {

        if (blogRepository.existsByTitle(dto.getTitle())) {
            throw new BlogAlreadyExistsException(
                    "Blog with this title already exists"
            );
        }

        Psychologist author = psychologistRepository.findById(dto.getAuthorId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Psychologist not found")
                );

        BlogPost post = blogMapper.toEntity(dto, author);

        return blogMapper.toDto(
                blogRepository.save(post)
        );
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
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Blog not found")
                        )
        );
    }
}