package com.psychcenter.backend.repository.test;

import com.psychcenter.backend.model.entity.test.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {}
