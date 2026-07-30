package com.devMoxy.football_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuessResponseDTO {
    private Long playerId;

    private Long achievementId;

    private boolean correct;
}
