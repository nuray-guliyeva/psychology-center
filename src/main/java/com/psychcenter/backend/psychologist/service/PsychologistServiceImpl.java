package com.psychcenter.backend.psychologist.service;

import com.psychcenter.backend.psychologist.entity.Psychologist;
import com.psychcenter.backend.psychologist.repository.PsychologistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PsychologistServiceImpl implements PsychologistService {

    private final PsychologistRepository repository;

    @Override
    public List<Psychologist> getAll() {
        return repository.findAll();
    }

    @Override
    public Psychologist getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Psychologist not found with id: " + id));
    }

    @Override
    public Psychologist create(Psychologist psychologist) {
        return repository.save(psychologist);
    }
}