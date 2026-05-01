package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}