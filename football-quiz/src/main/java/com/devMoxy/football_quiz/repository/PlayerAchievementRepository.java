package com.devMoxy.football_quiz.repository;

import com.devMoxy.football_quiz.entity.PlayerAchievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerAchievementRepository extends JpaRepository<PlayerAchievement, Long> {
    List<PlayerAchievement> findByAchievementId(Long achievementId);
}