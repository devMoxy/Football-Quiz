package com.devMoxy.football_quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ClubStint")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubStint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable  = false)
    private String clubName;

    @Column(nullable  = false)
    private String logoUrl;

    @Column(nullable = false)
    private int clubOrder;

    @ManyToOne
    private CareerPathQuestion careerPathQuestion;
}
