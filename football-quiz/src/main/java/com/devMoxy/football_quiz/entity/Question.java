package com.devMoxy.football_quiz.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Question")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String text;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    @Column(nullable = false)
    private int correctAnswerIndex;

    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;

    @ManyToOne
    private Category category;

}
