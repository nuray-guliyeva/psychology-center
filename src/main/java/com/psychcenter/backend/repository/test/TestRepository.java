package com.psychcenter.backend.repository.test;

import com.psychcenter.backend.model.entity.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {}
