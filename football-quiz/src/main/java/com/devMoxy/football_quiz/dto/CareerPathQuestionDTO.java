package com.devMoxy.football_quiz.dto;

import com.devMoxy.football_quiz.entity.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerPathQuestionDTO {
    private Long id;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private Difficulty difficulty;

    private List<ClubStintDTO> clubStints;
}
