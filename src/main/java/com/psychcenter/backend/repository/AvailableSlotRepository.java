package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.AvailableSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailableSlotRepository extends JpaRepository<AvailableSlot, Long> {

    List<AvailableSlot> findByPsychologistIdAndDateAndBookedFalse(
            Long psychologistId,
            LocalDate date
    );
}