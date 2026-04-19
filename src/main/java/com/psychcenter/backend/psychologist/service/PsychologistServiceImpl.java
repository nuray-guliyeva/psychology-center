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
}