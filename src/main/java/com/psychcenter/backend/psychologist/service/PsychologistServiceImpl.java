package com.psychcenter.backend.psychologist.service;

import com.psychcenter.backend.common.exception.base.ErrorCode;
import com.psychcenter.backend.common.exception.base.BaseException;
import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.entity.Psychologist;
import com.psychcenter.backend.psychologist.mapper.PsychologistMapper;
import com.psychcenter.backend.psychologist.repository.PsychologistRepository;
import com.psychcenter.backend.psychologist.specification.PsychologistSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository repository;
    private final PsychologistMapper mapper;

    @Override
    public Page<PsychologistResponseDto> getAll(
            String specialization,
            String language,
            Integer minExperience,
            Pageable pageable
    ) {

        Specification<Psychologist> spec = Specification
                .where(PsychologistSpecification.hasSpecialization(specialization))
                .and(PsychologistSpecification.hasLanguage(language))
                .and(PsychologistSpecification.hasMinExperience(minExperience));

        return repository.findAll(spec, pageable)
                .map(mapper::toDto);
    }

    @Override
    public PsychologistResponseDto getById(Long id) {
        Psychologist psychologist = repository.findById(id)
                .orElseThrow(() ->
                        new BaseException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "Psychologist not found with id: " + id,
                                HttpStatus.NOT_FOUND
                        )
                );

        return mapper.toDto(psychologist);
    }

    @Override
    public PsychologistResponseDto create(PsychologistRequestDto dto) {
        Psychologist entity = mapper.toEntity(dto);
        Psychologist saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public PsychologistResponseDto update(Long id, PsychologistRequestDto dto) {

        Psychologist existing = repository.findById(id)
                .orElseThrow(() ->
                        new BaseException(
                                ErrorCode.RESOURCE_NOT_FOUND,
                                "Psychologist not found with id: " + id,
                                HttpStatus.NOT_FOUND
                        )
                );

        mapper.updateEntity(existing, dto);

        Psychologist updated = repository.save(existing);

        return mapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new BaseException(
                    ErrorCode.RESOURCE_NOT_FOUND,
                    "Psychologist not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }
        repository.deleteById(id);
    }
}