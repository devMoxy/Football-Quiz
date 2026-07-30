package com.devMoxy.football_quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AchievementMatchStartResponseDTO {
    private int gridSize;

    private int totalPlayers;

    private List<AchievementDTO> achievements;

    private List<PlayerDTO> players;

    private Map<Long, PlayerDTO> backupPools;
}
