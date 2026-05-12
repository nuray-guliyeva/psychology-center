package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.response.VacancyResponseDto;
import com.psychcenter.backend.mapper.VacancyMapper;
import com.psychcenter.backend.model.entity.Vacancy;
import com.psychcenter.backend.repository.VacancyRepository;
import com.psychcenter.backend.service.VacancyService;
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