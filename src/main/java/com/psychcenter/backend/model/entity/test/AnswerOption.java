package com.psychcenter.backend.model.entity.test;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private int score;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}