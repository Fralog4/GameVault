package com.project.projectCS50x.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "videogames")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Videogame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String platform;
    @Enumerated(EnumType.STRING)
    private GameGenre genre;
    private LocalDate playedDate;
    private int personalRating;
    @Enumerated(EnumType.STRING)
    private Gamestatus gamestatus;
}
