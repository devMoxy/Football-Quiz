package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.*;
import com.devMoxy.football_quiz.service.AchievementMatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AchievementMatchController {
    private final AchievementMatchService achievementMatchService;

    public AchievementMatchController(AchievementMatchService achievementMatchService) {
        this.achievementMatchService = achievementMatchService;
    }

    @PostMapping("/api/achievement-match/start")
    public AchievementMatchStartResponseDTO startRound(@RequestParam int gridSize){
        return achievementMatchService.startRound(gridSize);
    }

    @PostMapping("/api/achievement-match/guess")
    public GuessResponseDTO guess(@RequestBody GuessRequestDTO dto){
        return achievementMatchService.verifyGuess(dto.getPlayerId(), dto.getAchievementId());
    }

    @PostMapping("/api/achievement-match/lifeline")
    public LifelineResponseDTO lifeline(@RequestBody LifelineRequestDTO dto){
        return achievementMatchService.useLifeline(dto.getPlayerId(), dto.getBoardAchievementIds());
    }
}