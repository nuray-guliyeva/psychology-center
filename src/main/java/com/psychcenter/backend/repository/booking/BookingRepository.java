package com.psychcenter.backend.repository.booking;

import com.psychcenter.backend.model.entity.booking.Booking;
import com.psychcenter.backend.model.entity.psychologist.Psychologist;
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