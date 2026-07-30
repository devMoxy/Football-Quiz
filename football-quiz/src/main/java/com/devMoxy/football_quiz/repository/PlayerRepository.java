package com.devMoxy.football_quiz.repository;

import com.devMoxy.football_quiz.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}