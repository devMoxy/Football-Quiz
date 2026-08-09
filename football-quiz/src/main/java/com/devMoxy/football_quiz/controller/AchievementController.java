package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.AchievementCreateDTO;
import com.devMoxy.football_quiz.dto.AchievementDTO;
import com.devMoxy.football_quiz.dto.PlayerAchievementCreateDTO;
import com.devMoxy.football_quiz.dto.PlayerAchievementDTO;
import com.devMoxy.football_quiz.service.AchievementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AchievementController {
    private final AchievementService achievementService;

    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @GetMapping("/api/achievements")
    public List<AchievementDTO> getAchievements() {
        return achievementService.getAllAchievements();
    }

    @PostMapping("/api/achievements")
    public AchievementDTO postAchievement(@Valid @RequestBody AchievementCreateDTO dto) {
        return achievementService.createAchievement(dto);
    }

    @PostMapping("/api/player-achievements")
    public PlayerAchievementDTO postPlayerAchievement(@Valid @RequestBody PlayerAchievementCreateDTO dto) {
        return achievementService.linkPlayerAchievement(dto);
    }
}
