package com.devMoxy.football_quiz.repository;

import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDifficulty(Difficulty difficulty);

    List<Question> findByDifficultyAndCategory_Id(Difficulty difficulty, Long categoryId);
}
