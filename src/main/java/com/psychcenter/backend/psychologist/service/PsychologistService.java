package com.psychcenter.backend.psychologist.service;

import com.psychcenter.backend.psychologist.entity.Psychologist;

import java.util.List;

public interface PsychologistService {

    List<Psychologist> getAll();

    Psychologist getById(Long id);

    Psychologist create(Psychologist psychologist);
}