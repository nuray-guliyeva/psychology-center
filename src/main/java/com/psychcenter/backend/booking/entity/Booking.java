package com.psychcenter.backend.booking.entity;

import com.psychcenter.backend.psychologist.entity.Psychologist;
import com.psychcenter.backend.user.entity.User;
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