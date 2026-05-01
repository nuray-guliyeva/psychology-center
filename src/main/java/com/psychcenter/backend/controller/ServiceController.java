package com.psychcenter.backend.controller;

import com.psychcenter.backend.model.entity.ServiceEntity;
import com.psychcenter.backend.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceRepository repository;

    @PostMapping
    public ServiceEntity create(@RequestBody ServiceEntity s) {
        return repository.save(s);
    }

    @GetMapping
    public List<ServiceEntity> getAll() {
        return repository.findAll();
    }
}