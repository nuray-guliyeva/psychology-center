package com.psychcenter.backend.service.vacancy.impl;

import com.psychcenter.backend.dto.response.VacancyResponseDto;
import com.psychcenter.backend.mapper.vacancy.VacancyMapper;
import com.psychcenter.backend.model.entity.vacancy.Vacancy;
import com.psychcenter.backend.repository.vacancy.VacancyRepository;
import com.psychcenter.backend.service.vacancy.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository repository;
    private final VacancyMapper mapper;

    @Override
    public VacancyResponseDto create(String position,
                                     String location,
                                     String type,
                                     String description,
                                     String fileName) {

        Vacancy v = Vacancy.builder()
                .position(position)
                .location(location)
                .type(type)
                .description(description)
                .build();

        return mapper.toDto(repository.save(v));
    }

    @Override
    public List<VacancyResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}