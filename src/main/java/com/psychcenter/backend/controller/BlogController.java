package com.psychcenter.backend.controller;

import com.psychcenter.backend.model.entity.BlogPost;
import com.psychcenter.backend.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService service;

    @PostMapping
    public BlogPost create(@RequestBody BlogPost post) {
        return service.create(post);
    }

    @GetMapping
    public Page<BlogPost> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable Long id) {
        return service.getById(id);
    }
}