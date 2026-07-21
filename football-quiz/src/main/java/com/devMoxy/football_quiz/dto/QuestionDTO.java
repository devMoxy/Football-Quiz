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
public class QuestionDTO {
    private long id;

    private String text;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private Difficulty difficulty;

    private String categoryName;
}
