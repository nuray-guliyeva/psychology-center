package com.psychcenter.backend.service.impl;

import com.psychcenter.backend.common.exception.base.ResourceNotFoundException;
import com.psychcenter.backend.dto.request.PsychologistRequestDto;
import com.psychcenter.backend.dto.response.PsychologistResponseDto;
import com.psychcenter.backend.mapper.PsychologistMapper;
import com.psychcenter.backend.model.entity.Psychologist;
import com.psychcenter.backend.model.entity.User;
import com.psychcenter.backend.model.enums.Role;
import com.psychcenter.backend.repository.PsychologistRepository;
import com.psychcenter.backend.repository.UserRepository;
import com.psychcenter.backend.service.PsychologistService;
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
@Transactional(readOnly = true)
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository psychologistRepository;
    private final PsychologistMapper psychologistMapper;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public PsychologistResponseDto create(PsychologistRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        user.setRole(Role.PSYCHOLOGIST);

        Psychologist p = psychologistMapper.toEntity(dto);

        p.setUser(user);
        user.setPsychologist(p);

        return psychologistMapper.toDto(psychologistRepository.save(p));
    }

    @Override
    @Transactional
    public PsychologistResponseDto update(Long id, PsychologistRequestDto dto) {
        Psychologist p = psychologistRepository.findById(id)
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

        return psychologistMapper.toDto(psychologistRepository.save(p));
    }

    @Override
    @Transactional
    public void delete(Long id) {

        Psychologist psychologist = psychologistRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Psychologist not found with id: " + id
                        )
                );

        psychologistRepository.delete(psychologist);
    }

    @Override
    public Page<PsychologistResponseDto> getAll(int page, int size) {
        return psychologistRepository.findAll(PageRequest.of(page, size))
                .map(psychologistMapper::toDto);
    }

    @Override
    public PsychologistResponseDto getById(Long id) {

        Psychologist psychologist = psychologistRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Psychologist not found with id: " + id
                        )
                );

        return psychologistMapper.toDto(psychologist);
    }

    @Override
    public List<PsychologistResponseDto> filter(String specialization, String language) {

        Specification<Psychologist> spec = (root, query, cb) -> cb.conjunction();
        if (specialization != null) {
            spec = spec.and(hasSpecialization(specialization));
        }

        if (language != null) {
            spec = spec.and(hasLanguage(language));
        }

        return psychologistRepository.findAll(spec)
                .stream()
                .map(psychologistMapper::toDto)
                .toList();
    }

    @Override
    public List<PsychologistResponseDto> recommend(String level) {

        List<Psychologist> list = psychologistRepository.findAll();

        return list.stream()
                .filter(p -> switch (level) {
                    case "HIGH" -> p.getSpecialization() != null &&
                            p.getSpecialization().contains("Clinical");

                    case "MEDIUM" -> p.getSpecialization() != null &&
                            p.getSpecialization().contains("Therapy");

                    default -> true;
                })
                .map(psychologistMapper::toDto)
                .toList();
    }
}