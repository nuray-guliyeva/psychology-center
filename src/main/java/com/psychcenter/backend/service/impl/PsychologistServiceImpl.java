package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.mapper.PsychologistMapper;
import com.psychcenter.backend.model.entity.Psychologist;
import com.psychcenter.backend.repository.PsychologistRepository;
import com.psychcenter.backend.service.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.psychcenter.backend.specification.PsychologistSpecification.hasLanguage;
import static com.psychcenter.backend.specification.PsychologistSpecification.hasSpecialization;

@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository repository;
    private final PsychologistMapper mapper;

    @Override
    public PsychologistResponseDto create(PsychologistRequestDto dto) {
        Psychologist p = mapper.toEntity(dto);
        return mapper.toDto(repository.save(p));
    }

    @Override
    public PsychologistResponseDto update(Long id, PsychologistRequestDto dto) {
        Psychologist p = repository.findById(id).orElseThrow();

        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setSpecialization(dto.getSpecialization());
        p.setExperienceYears(dto.getExperienceYears());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        p.setBio(dto.getBio());
        p.setEducation(dto.getEducation());
        p.setCertificates(dto.getCertificates());
        p.setLanguages(dto.getLanguages());
        p.setApproach(dto.getApproach());

        return mapper.toDto(repository.save(p));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<PsychologistResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public PsychologistResponseDto getById(Long id) {
        return mapper.toDto(
                repository.findById(id).orElseThrow()
        );
    }

    @Override
    public List<PsychologistResponseDto> filter(String specialization, String language) {

        var spec = Specification
                .where(hasSpecialization(specialization))
                .and(hasLanguage(language));

        return repository.findAll(spec)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}