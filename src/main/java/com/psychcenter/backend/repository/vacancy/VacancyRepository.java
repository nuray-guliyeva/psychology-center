package com.psychcenter.backend.repository.vacancy;

import com.psychcenter.backend.model.entity.vacancy.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
}