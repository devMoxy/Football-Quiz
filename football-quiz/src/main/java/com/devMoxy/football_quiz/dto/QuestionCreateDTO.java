package com.devMoxy.football_quiz.dto;

import com.devMoxy.football_quiz.entity.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreateDTO {
    @NotBlank
    private String text;

    @NotBlank
    private String optionA;

    @NotBlank
    private String optionB;

    @NotBlank
    private String optionC;

    @NotBlank
    private String optionD;

    @Min(0)
    @Max(3)
    private int correctAnswerIndex;

    @NotNull
    private Difficulty difficulty;

    @NotNull
    private Long categoryId;
}
