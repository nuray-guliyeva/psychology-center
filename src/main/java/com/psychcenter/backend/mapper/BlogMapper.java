package com.psychcenter.backend.mapper;

import com.psychcenter.backend.dto.response.BlogResponseDto;
import com.psychcenter.backend.model.entity.BlogPost;
import org.springframework.stereotype.Component;

@Component
public class BlogMapper {

    public BlogResponseDto toDto(BlogPost post) {
        return BlogResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .author(post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName())
                .createdAt(post.getCreatedAt())
                .build();
    }
}