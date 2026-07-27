package com.devMoxy.football_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerPathResultDTO {
    private Long questionId;

    private int selectedAnswerIndex;

    private int correctAnswerIndex;

    private String correctPlayerName;

    private boolean correct;
}
