package com.psychcenter.backend.repository.psychologist;

import com.psychcenter.backend.model.entity.psychologist.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PsychologistRepository extends
        JpaRepository<Psychologist, Long>,
        JpaSpecificationExecutor<Psychologist> {
}