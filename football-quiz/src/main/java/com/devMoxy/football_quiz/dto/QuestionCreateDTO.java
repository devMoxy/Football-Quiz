package com.devMoxy.football_quiz.dto;

import com.devMoxy.football_quiz.entity.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreateDTO {
    private String text;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private int correctAnswerIndex;

    private Difficulty difficulty;

    private Long categoryId;
}
