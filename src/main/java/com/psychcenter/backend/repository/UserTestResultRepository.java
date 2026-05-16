package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.UserTestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTestResultRepository extends JpaRepository<UserTestResult, Long> {

    List<UserTestResult> findByUserId(Long userId);
}