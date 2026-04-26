package com.psychcenter.backend.psychologist.service;

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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository repository;

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
                .map(PsychologistMapper::toDto);
    }

    @Override
    public PsychologistResponseDto getById(Long id) {
        Psychologist psychologist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologist not found with id: " + id));

        return PsychologistMapper.toDto(psychologist);
    }

    @Override
    public PsychologistResponseDto create(PsychologistRequestDto dto) {
        Psychologist entity = PsychologistMapper.toEntity(dto);
        Psychologist saved = repository.save(entity);
        return PsychologistMapper.toDto(saved);
    }

    @Override
    public PsychologistResponseDto update(Long id, PsychologistRequestDto dto) {

        Psychologist existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologist not found with id: " + id));

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setSpecialization(dto.getSpecialization());
        existing.setExperienceYears(dto.getExperienceYears());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setBio(dto.getBio());
        existing.setEducation(dto.getEducation());
        existing.setCertificates(dto.getCertificates());
        existing.setLanguages(dto.getLanguages());
        existing.setApproach(dto.getApproach());

        Psychologist updated = repository.save(existing);

        return PsychologistMapper.toDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Psychologist not found with id: " + id);
        }
        repository.deleteById(id);
    }
}