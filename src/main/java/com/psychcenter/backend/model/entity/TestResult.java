package com.psychcenter.backend.model.entity;

import com.psychcenter.backend.model.enums.TestLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String testName;

    private int score;

    @Enumerated(EnumType.STRING)
    private TestLevel level;
}