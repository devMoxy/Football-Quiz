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
public class LifelineResponseDTO {
    private Long playerId;

    private List<Long> matchedAchievementIds;
}
