package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.AchievementDTO;
import com.devMoxy.football_quiz.dto.AchievementMatchStartResponseDTO;
import com.devMoxy.football_quiz.dto.GuessResponseDTO;
import com.devMoxy.football_quiz.dto.PlayerDTO;
import com.devMoxy.football_quiz.entity.Achievement;
import com.devMoxy.football_quiz.entity.Player;
import com.devMoxy.football_quiz.entity.PlayerAchievement;
import com.devMoxy.football_quiz.repository.AchievementRepository;
import com.devMoxy.football_quiz.repository.PlayerAchievementRepository;
import com.devMoxy.football_quiz.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AchievementMatchServiceTest {

    private static final int GRID_SIZE = 2; // 4 achievements, each with 2 unique qualifying players

    private AchievementMatchService service;

    // achievementId -> {playerId, playerId} exclusive to that achievement (no overlap across achievements)
    private final Map<Long, Set<Long>> playersByAchievement = new HashMap<>();

    @BeforeEach
    void setUp() {
        PlayerRepository playerRepository = mock(PlayerRepository.class);
        AchievementRepository achievementRepository = mock(AchievementRepository.class);
        PlayerAchievementRepository playerAchievementRepository = mock(PlayerAchievementRepository.class);

        List<Achievement> achievements = new ArrayList<>();
        for (long achievementId = 1; achievementId <= 4; achievementId++) {
            achievements.add(new Achievement(achievementId, "desc" + achievementId, "img" + achievementId));

            long p1 = achievementId * 10 + 1;
            long p2 = achievementId * 10 + 2;
            Player player1 = new Player(p1, "Player" + p1, "img" + p1);
            Player player2 = new Player(p2, "Player" + p2, "img" + p2);

            playersByAchievement.put(achievementId, new HashSet<>(List.of(p1, p2)));

            List<PlayerAchievement> pas = List.of(
                    new PlayerAchievement(null, player1, achievements.get(achievements.size() - 1)),
                    new PlayerAchievement(null, player2, achievements.get(achievements.size() - 1))
            );
            when(playerAchievementRepository.findByAchievementId(achievementId)).thenReturn(pas);
        }
        // findAll() is shuffled in-place by the service; returning the same backing list each
        // call is fine since gridSize*gridSize == achievements.size() here (limit == full list).
        when(achievementRepository.findAll()).thenReturn(achievements);

        service = new AchievementMatchService(playerRepository, achievementRepository, playerAchievementRepository);
    }

    @Test
    void playerOrderIsNotCorrelatedWithAchievementOrder() {
        int trials = 200;
        int alignedAtPositionZero = 0;

        for (int i = 0; i < trials; i++) {
            AchievementMatchStartResponseDTO response = service.startRound(GRID_SIZE);
            AchievementDTO firstAchievement = response.getAchievements().get(0);
            PlayerDTO firstPlayer = response.getPlayers().get(0);

            Set<Long> playersForFirstAchievement = playersByAchievement.get(firstAchievement.getAchievementId());
            if (playersForFirstAchievement.contains(firstPlayer.getPlayerId())) {
                alignedAtPositionZero++;
            }
        }

        // Pre-fix, players.get(0) was ALWAYS the primary for achievements.get(0) -> alignedAtPositionZero == trials.
        // Post-fix, alignment should be roughly 1-in-4 (independent shuffle), never 100%.
        System.out.println("players[0] aligned with achievements[0] in " + alignedAtPositionZero + "/" + trials + " rounds");
        assertFalse(alignedAtPositionZero == trials,
                "players[0] matched achievements[0] every single round - order still correlated");
        assertTrue(alignedAtPositionZero < trials / 2,
                "alignment rate suspiciously high for an independent shuffle: " + alignedAtPositionZero + "/" + trials);
    }

    @Test
    void guessAndBackupsStillResolveCorrectlyAfterShuffle() {
        AchievementMatchStartResponseDTO response = service.startRound(GRID_SIZE);

        for (AchievementDTO achievementDto : response.getAchievements()) {
            Long achievementId = achievementDto.getAchievementId();
            Set<Long> qualifyingPlayerIds = playersByAchievement.get(achievementId);

            PlayerDTO backup = response.getBackupPools().get(achievementId);
            assertTrue(qualifyingPlayerIds.contains(backup.getPlayerId()),
                    "backup pool for achievement " + achievementId + " is keyed correctly and unaffected by player-list shuffle");

            GuessResponseDTO correctGuess = service.verifyGuess(backup.getPlayerId(), achievementId);
            assertTrue(correctGuess.isCorrect(), "backup player must still resolve as correct for their own achievement");

            GuessResponseDTO wrongGuess = service.verifyGuess(9999L, achievementId);
            assertFalse(wrongGuess.isCorrect(), "unrelated player must not resolve as correct");
        }

        for (PlayerDTO playerDto : response.getPlayers()) {
            long primaryAchievementId = playersByAchievement.entrySet().stream()
                    .filter(e -> e.getValue().contains(playerDto.getPlayerId()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow();

            GuessResponseDTO correctGuess = service.verifyGuess(playerDto.getPlayerId(), primaryAchievementId);
            assertTrue(correctGuess.isCorrect(), "displayed primary player must still resolve correctly regardless of list position");
        }

        assertEquals(4, response.getPlayers().size());
        assertEquals(4, response.getAchievements().size());
        assertEquals(4, response.getBackupPools().size());
    }
}
