package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
