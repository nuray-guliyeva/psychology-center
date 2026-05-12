package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {}
