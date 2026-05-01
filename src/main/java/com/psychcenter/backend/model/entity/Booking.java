package com.psychcenter.backend.model.entity;

import com.psychcenter.backend.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Psychologist psychologist;

    private LocalDate date;
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}