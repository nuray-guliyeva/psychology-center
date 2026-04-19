package com.psychcenter.backend.psychologist.repository;

import com.psychcenter.backend.psychologist.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
}