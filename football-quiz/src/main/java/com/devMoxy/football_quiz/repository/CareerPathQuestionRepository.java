package com.devMoxy.football_quiz.repository;

import com.devMoxy.football_quiz.entity.CareerPathQuestion;
import com.devMoxy.football_quiz.entity.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerPathQuestionRepository extends JpaRepository<CareerPathQuestion, Long> {
    List<CareerPathQuestion> findByDifficulty(Difficulty difficulty);
}
