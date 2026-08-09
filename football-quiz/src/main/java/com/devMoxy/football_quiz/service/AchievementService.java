package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.AchievementCreateDTO;
import com.devMoxy.football_quiz.dto.AchievementDTO;
import com.devMoxy.football_quiz.dto.PlayerAchievementCreateDTO;
import com.devMoxy.football_quiz.dto.PlayerAchievementDTO;
import com.devMoxy.football_quiz.dto.PlayerDTO;
import com.devMoxy.football_quiz.entity.Achievement;
import com.devMoxy.football_quiz.entity.Player;
import com.devMoxy.football_quiz.entity.PlayerAchievement;
import com.devMoxy.football_quiz.repository.AchievementRepository;
import com.devMoxy.football_quiz.repository.PlayerAchievementRepository;
import com.devMoxy.football_quiz.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AchievementService {
    private final AchievementRepository achievementRepository;
    private final PlayerRepository playerRepository;
    private final PlayerAchievementRepository playerAchievementRepository;

    public AchievementService(AchievementRepository achievementRepository, PlayerRepository playerRepository, PlayerAchievementRepository playerAchievementRepository) {
        this.achievementRepository = achievementRepository;
        this.playerRepository = playerRepository;
        this.playerAchievementRepository = playerAchievementRepository;
    }

    private AchievementDTO convertToDto(Achievement achievement) {
        AchievementDTO dto = new AchievementDTO();
        dto.setAchievementId(achievement.getId());
        dto.setDescription(achievement.getDescription());
        dto.setImageUrl(achievement.getImageUrl());
        return dto;
    }

    private PlayerDTO convertPlayerToDto(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(player.getId());
        dto.setName(player.getName());
        dto.setImageUrl(player.getImageUrl());
        return dto;
    }

    public List<AchievementDTO> getAllAchievements() {
        List<Achievement> achievements = achievementRepository.findAll();
        List<AchievementDTO> dtoList = new ArrayList<>();

        for (Achievement achievement : achievements) {
            dtoList.add(convertToDto(achievement));
        }
        return dtoList;
    }

    public AchievementDTO createAchievement(AchievementCreateDTO dto) {
        Achievement achievement = new Achievement();
        achievement.setDescription(dto.getDescription());
        achievement.setImageUrl(dto.getImageUrl());
        Achievement saved = achievementRepository.save(achievement);
        return convertToDto(saved);
    }

    public PlayerAchievementDTO linkPlayerAchievement(PlayerAchievementCreateDTO dto) {
        Player player = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player not found"));
        Achievement achievement = achievementRepository.findById(dto.getAchievementId())
                .orElseThrow(() -> new RuntimeException("Achievement not found"));

        if (playerAchievementRepository.existsByPlayer_IdAndAchievement_Id(dto.getPlayerId(), dto.getAchievementId())) {
            throw new RuntimeException("This player is already linked to this achievement");
        }

        PlayerAchievement playerAchievement = new PlayerAchievement();
        playerAchievement.setPlayer(player);
        playerAchievement.setAchievement(achievement);
        PlayerAchievement saved = playerAchievementRepository.save(playerAchievement);

        PlayerAchievementDTO result = new PlayerAchievementDTO();
        result.setId(saved.getId());
        result.setPlayer(convertPlayerToDto(player));
        result.setAchievement(convertToDto(achievement));
        return result;
    }
}
