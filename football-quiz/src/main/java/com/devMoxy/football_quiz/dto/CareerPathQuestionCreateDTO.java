package com.devMoxy.football_quiz.dto;

import com.devMoxy.football_quiz.entity.Difficulty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerPathQuestionCreateDTO {

    @NotBlank
    private String correctPlayerName;

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

    @NotEmpty
    private List<ClubStintDTO> clubStints;
}
