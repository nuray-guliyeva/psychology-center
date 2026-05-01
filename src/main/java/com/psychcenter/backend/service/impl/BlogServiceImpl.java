package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.model.entity.BlogPost;
import com.psychcenter.backend.repository.BlogRepository;
import com.psychcenter.backend.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository repository;

    @Override
    public BlogPost create(BlogPost post) {
        post.setCreatedAt(LocalDateTime.now());
        return repository.save(post);
    }

    @Override
    public Page<BlogPost> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public BlogPost getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}