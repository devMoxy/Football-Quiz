package com.devMoxy.football_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareerPathQuizResultDTO {
    private int score;
    private int totalQuestions;
    private List<CareerPathResultDTO> results;
}