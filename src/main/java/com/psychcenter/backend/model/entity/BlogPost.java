package com.psychcenter.backend.model.entity;

import com.psychcenter.backend.model.enums.BlogCategory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "blog_posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String content;

    @Enumerated(EnumType.STRING)
    private BlogCategory category;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Psychologist author;

    private LocalDateTime createdAt;
}