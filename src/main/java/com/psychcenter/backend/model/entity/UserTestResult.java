package com.psychcenter.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalScore;

    private String level;

    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @ManyToOne
    private Test test;
}