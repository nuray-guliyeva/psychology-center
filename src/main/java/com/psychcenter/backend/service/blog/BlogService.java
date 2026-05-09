package com.psychcenter.backend.service.blog;

import com.psychcenter.backend.dto.response.BlogResponseDto;
import com.psychcenter.backend.model.entity.blog.BlogPost;
import org.springframework.data.domain.Page;

public interface BlogService {

    BlogResponseDto create(BlogPost post);

    Page<BlogResponseDto> getAll(int page, int size);

    BlogResponseDto getById(Long id);
}