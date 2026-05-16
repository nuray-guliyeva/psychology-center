package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.response.VacancyResponseDto;
import com.psychcenter.backend.mapper.VacancyMapper;
import com.psychcenter.backend.model.entity.Vacancy;
import com.psychcenter.backend.repository.VacancyRepository;
import com.psychcenter.backend.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Override
    @Transactional
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
                .fileName(fileName)
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