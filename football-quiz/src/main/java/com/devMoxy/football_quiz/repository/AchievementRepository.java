package com.devMoxy.football_quiz.repository;

import com.devMoxy.football_quiz.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, Long> {
}