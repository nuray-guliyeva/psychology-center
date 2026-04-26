package com.psychcenter.backend.psychologist.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "psychologists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Psychologist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String specialization;

    private Integer experienceYears;

    private String email;
    private String phone;

    @Column(length = 2000)
    private String bio;

    private String education;

    @ElementCollection
    private List<String> certificates;

    @ElementCollection
    private List<String> languages;

    private String approach;

    private Double rating;
}