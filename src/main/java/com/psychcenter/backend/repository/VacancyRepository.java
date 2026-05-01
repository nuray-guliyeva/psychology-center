package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}