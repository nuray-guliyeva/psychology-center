package com.psychcenter.backend.service.psychologist.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.mapper.psychologist.PsychologistMapper;
import com.psychcenter.backend.model.entity.psychologist.Psychologist;
import com.psychcenter.backend.model.entity.user.User;
import com.psychcenter.backend.repository.psychologist.PsychologistRepository;
import com.psychcenter.backend.repository.user.UserRepository;
import com.psychcenter.backend.service.psychologist.PsychologistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.psychcenter.backend.specification.PsychologistSpecification.hasLanguage;
import static com.psychcenter.backend.specification.PsychologistSpecification.hasSpecialization;

@Service
@RequiredArgsConstructor
@Transactional
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository repository;
    private final PsychologistMapper mapper;
    private final UserRepository userRepository;

    @Override
    public PsychologistResponseDto create(PsychologistRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Psychologist p = mapper.toEntity(dto);

        p.setUser(user);
        user.setPsychologist(p);

        return mapper.toDto(repository.save(p));
    }

    @Override
    public PsychologistResponseDto update(Long id, PsychologistRequestDto dto) {
        Psychologist p = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psychologist not found with id: " + id));


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
    public Page<PsychologistResponseDto> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size))
                .map(mapper::toDto);
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