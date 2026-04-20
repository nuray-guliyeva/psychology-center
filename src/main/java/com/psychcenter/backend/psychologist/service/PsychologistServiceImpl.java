package com.psychcenter.backend.psychologist.service;

import com.psychcenter.backend.psychologist.dto.PsychologistRequestDto;
import com.psychcenter.backend.psychologist.dto.PsychologistResponseDto;
import com.psychcenter.backend.psychologist.entity.Psychologist;
import com.psychcenter.backend.psychologist.mapper.PsychologistMapper;
import com.psychcenter.backend.psychologist.repository.PsychologistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository repository;

    @Override
    public List<PsychologistResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(PsychologistMapper::toDto)
                .toList();
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