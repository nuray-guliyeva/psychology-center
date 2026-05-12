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

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

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

        return vacancyMapper.toDto(vacancyRepository.save(v));
    }

    @Override
    public List<VacancyResponseDto> getAll() {
        return vacancyRepository.findAll()
                .stream()
                .map(vacancyMapper::toDto)
                .toList();
    }
}