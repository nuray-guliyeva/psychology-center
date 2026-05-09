package com.psychcenter.backend.repository.service;

import com.psychcenter.backend.model.entity.service.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}