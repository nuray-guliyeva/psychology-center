package com.psychcenter.backend.service;

import com.psychcenter.backend.dto.response.VacancyResponseDto;

import java.util.List;

public interface VacancyService {

    VacancyResponseDto create(String position,
                              String location,
                              String type,
                              String description,
                              String fileName);

    List<VacancyResponseDto> getAll();
}