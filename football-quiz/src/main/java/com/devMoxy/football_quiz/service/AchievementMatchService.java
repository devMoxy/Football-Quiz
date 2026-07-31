package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.*;
import com.devMoxy.football_quiz.entity.Achievement;
import com.devMoxy.football_quiz.entity.Player;
import com.devMoxy.football_quiz.entity.PlayerAchievement;
import com.devMoxy.football_quiz.repository.AchievementRepository;
import com.devMoxy.football_quiz.repository.PlayerAchievementRepository;
import com.devMoxy.football_quiz.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AchievementMatchService {
    private final PlayerRepository playerRepository;
    private final AchievementRepository achievementRepository;
    private final PlayerAchievementRepository playerAchievementRepository;

    public AchievementMatchService(PlayerRepository playerRepository, AchievementRepository achievementRepository, PlayerAchievementRepository playerAchievementRepository) {
        this.playerRepository = playerRepository;
        this.achievementRepository = achievementRepository;
        this.playerAchievementRepository = playerAchievementRepository;
    }

    private List<Achievement> pickRandomAchievements(int count){
        List<Achievement> allAchievements = achievementRepository.findAll();
        Collections.shuffle(allAchievements);
        int limit = Math.min(count, allAchievements.size());
        return new ArrayList<>(allAchievements.subList(0, limit));
    }

    private List<Player> pickPrimaryAndBackup(Long achievementId){
        List<PlayerAchievement> playerAchievements = playerAchievementRepository.findByAchievementId(achievementId);

        List<Player> qualifyingPlayers = new ArrayList<>();
        for (PlayerAchievement pa : playerAchievements) {
            qualifyingPlayers.add(pa.getPlayer());
        }

        if (qualifyingPlayers.size() < 2) {
            throw new RuntimeException("Achievement " + achievementId + " does not have at least 2 qualifying players");
        }

        Collections.shuffle(qualifyingPlayers);
        return qualifyingPlayers.subList(0, 2);
    }

    public void buildRoundDataTest(int gridSize){
        int achievementCount = gridSize * gridSize;
        List<Achievement> achievements = pickRandomAchievements(achievementCount);

        List<Player> players = new ArrayList<>();
        Map<Long, Player> backupPools = new HashMap<>();

        for (Achievement achievement : achievements) {
            List<Player> primaryAndBackup = pickPrimaryAndBackup(achievement.getId());
            Player primary = primaryAndBackup.get(0);
            Player backup = primaryAndBackup.get(1);

            players.add(primary);
            backupPools.put(achievement.getId(), backup);
        }
    }

    private PlayerDTO convertPlayerToDto(Player player){
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(player.getId());
        dto.setName(player.getName());
        dto.setImageUrl(player.getImageUrl());
        return dto;
    }

    private AchievementDTO convertAchievementToDto(Achievement achievement){
        AchievementDTO dto = new AchievementDTO();
        dto.setAchievementId(achievement.getId());
        dto.setDescription(achievement.getDescription());
        dto.setImageUrl(achievement.getImageUrl());
        return dto;
    }

    public AchievementMatchStartResponseDTO startRound(int gridSize){
        int achievementCount = gridSize * gridSize;
        List<Achievement> achievements = pickRandomAchievements(achievementCount);

        List<Player> players = new ArrayList<>();
        Map<Long, Player> backupPools = new HashMap<>();

        for (Achievement achievement : achievements) {
            List<Player> primaryAndBackup = pickPrimaryAndBackup(achievement.getId());
            Player primary = primaryAndBackup.get(0);
            Player backup = primaryAndBackup.get(1);

            players.add(primary);
            backupPools.put(achievement.getId(), backup);
        }

        List<AchievementDTO> achievementDtoList = new ArrayList<>();
        for(Achievement achievement : achievements){
            AchievementDTO dto = convertAchievementToDto(achievement);
            achievementDtoList.add(dto);
        }

        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for(Player player : players){
            PlayerDTO dto = convertPlayerToDto(player);
            playerDTOList.add(dto);
        }

        Map<Long, PlayerDTO> backupPoolDtos = new HashMap<>();
        for (Map.Entry<Long, Player> entry : backupPools.entrySet()) {
            Long achievementId = entry.getKey();
            Player backupPlayer = entry.getValue();
            PlayerDTO backupPlayerDto = convertPlayerToDto(backupPlayer);
            backupPoolDtos.put(achievementId, backupPlayerDto);
        }

        AchievementMatchStartResponseDTO response = new AchievementMatchStartResponseDTO();
        response.setGridSize(gridSize);
        response.setTotalPlayers(gridSize * gridSize * 2);
        response.setAchievements(achievementDtoList);
        response.setPlayers(playerDTOList);
        response.setBackupPools(backupPoolDtos);
        return response;
    }

    public GuessResponseDTO verifyGuess(Long playerId, Long achievementId){
        List<PlayerAchievement> playerAchievements = playerAchievementRepository.findByAchievementId(achievementId);

        boolean correct = false;
        for(PlayerAchievement player : playerAchievements){
            if(player.getPlayer().getId().equals(playerId)){
                correct = true;
                break;
            }
        }

        GuessResponseDTO response = new GuessResponseDTO();
        response.setCorrect(correct);
        response.setPlayerId(playerId);
        response.setAchievementId(achievementId);

        return response;
    }

    public LifelineResponseDTO useLifeline(Long playerId, List<Long> boardAchievementIds){
        List<PlayerAchievement> playerAchievements = playerAchievementRepository.findByPlayerId(playerId);
        List<Long> matchedAchievementIds = new ArrayList<>();
        for (PlayerAchievement pa : playerAchievements) {
            Long achievementId = pa.getAchievement().getId();
            if (boardAchievementIds.contains(achievementId)) {
                matchedAchievementIds.add(achievementId);
            }
        }
        LifelineResponseDTO lifeLine  = new LifelineResponseDTO();
        lifeLine.setPlayerId(playerId);
        lifeLine.setMatchedAchievementIds(matchedAchievementIds);

        return lifeLine;
    }
}