package com.psychcenter.backend.mapper;

import com.psychcenter.backend.dto.request.BlogCreateRequestDto;
import com.psychcenter.backend.dto.response.BlogResponseDto;
import com.psychcenter.backend.model.entity.BlogPost;
import com.psychcenter.backend.model.entity.Psychologist;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BlogMapper {

    public BlogPost toEntity(
            BlogCreateRequestDto dto,
            Psychologist author
    ) {

        return BlogPost.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(dto.getCategory())
                .author(author)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public BlogResponseDto toDto(BlogPost post) {

        return BlogResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .category(post.getCategory())
                .author(
                        post.getAuthor().getFirstName()
                                + " "
                                + post.getAuthor().getLastName()
                )
                .createdAt(post.getCreatedAt())
                .build();
    }
}