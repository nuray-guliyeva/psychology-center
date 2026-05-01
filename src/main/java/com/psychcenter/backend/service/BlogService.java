package com.psychcenter.backend.service;

import com.psychcenter.backend.model.entity.BlogPost;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {

    BlogPost create(BlogPost post);

    Page<BlogPost> getAll(int page, int size);

    BlogPost getById(Long id);

}