package com.psychcenter.backend.repository;

import com.psychcenter.backend.model.entity.Booking;
import com.psychcenter.backend.model.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByPsychologistAndDateAndTime(
            Psychologist psychologist,
            LocalDate date,
            LocalTime time
    );

    List<Booking> findByPsychologistIdAndDate(
            Long psychologistId,
            LocalDate date
    );

    List<Booking> findByPsychologistId(Long psychologistId);

}