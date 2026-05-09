package com.psychcenter.backend.controller.vacancy;

import com.psychcenter.backend.common.api.ApiResponse;
import com.psychcenter.backend.dto.response.VacancyResponseDto;
import com.psychcenter.backend.service.vacancy.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService service;

    @PostMapping(consumes = "multipart/form-data")
    public ApiResponse<VacancyResponseDto> create(
            @RequestParam String position,
            @RequestParam String location,
            @RequestParam String type,
            @RequestParam String description,
            @RequestParam("file") MultipartFile file
    ) throws Exception {


        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (file.getContentType() == null ||
                !file.getContentType().equals("application/pdf")) {
            throw new RuntimeException("Only PDF files are allowed");
        }


        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

        Path path = Paths.get("uploads/" + fileName);

        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        return ApiResponse.success(
                service.create(position, location, type, description, fileName),
                "Vacancy created"
        );
    }

    @GetMapping
    public ApiResponse<List<VacancyResponseDto>> getAll() {
        return ApiResponse.success(service.getAll(), "Vacancies list");
    }
}