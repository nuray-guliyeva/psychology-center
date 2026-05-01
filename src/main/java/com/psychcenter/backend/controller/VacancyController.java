package com.psychcenter.backend.controller;

import com.psychcenter.backend.model.entity.Vacancy;
import com.psychcenter.backend.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyRepository repository;

    @PostMapping(consumes = "multipart/form-data")
    public Vacancy create(
            @RequestParam String position,
            @RequestParam String location,
            @RequestParam String type,
            @RequestParam String description,
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        java.nio.file.Path path = java.nio.file.Paths.get("uploads/" + fileName);

        java.nio.file.Files.createDirectories(path.getParent());
        java.nio.file.Files.write(path, file.getBytes());

        Vacancy v = Vacancy.builder()
                .position(position)
                .location(location)
                .type(type)
                .description(description)
                .build();

        return repository.save(v);
    }

    @GetMapping
    public List<Vacancy> getAll() {
        return repository.findAll();
    }
}