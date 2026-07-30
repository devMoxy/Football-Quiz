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
public class LifelineRequestDTO {
    private Long playerId;

    private List<Long> boardAchievementIds;
}
