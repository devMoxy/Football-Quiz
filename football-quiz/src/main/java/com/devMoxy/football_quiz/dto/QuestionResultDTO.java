package com.devMoxy.football_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResultDTO {
    private Long questionId;

    private int selectedAnswerIndex;

    private int correctAnswerIndex;

    private boolean correct;
}
