package com.devMoxy.football_quiz.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerAchievementCreateDTO {
    @NotNull
    private Long playerId;

    @NotNull
    private Long achievementId;
}
