package com.devMoxy.football_quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "CareerPathQuestion")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerPathQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable  = false)
    private String correctPlayerName;

    @Column(nullable  = false)
    private String optionA;

    @Column(nullable  = false)
    private String optionB;

    @Column(nullable  = false)
    private String optionC;

    @Column(nullable  = false)
    private String optionD;

    @Column(nullable = false)
    private int correctAnswerIndex;

    @Column(nullable  = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @OneToMany(mappedBy = "careerPathQuestion",cascade = CascadeType.ALL)
    private List<ClubStint> clubStints;
}
