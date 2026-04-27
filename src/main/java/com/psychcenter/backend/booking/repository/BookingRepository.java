package com.psychcenter.backend.booking.repository;

import com.psychcenter.backend.booking.entity.Booking;
import com.psychcenter.backend.psychologist.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByPsychologistAndDateAndTime(
            Psychologist psychologist,
            LocalDate date,
            LocalTime time
    );
}