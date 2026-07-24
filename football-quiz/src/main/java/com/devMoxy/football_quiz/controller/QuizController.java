package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.QuestionDTO;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.service.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuizController {

    private final QuestionService questionService;

    public QuizController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/api/quiz/start")
    public List<QuestionDTO> quizStart(@RequestParam Long categoryId,@RequestParam Difficulty difficulty, @RequestParam(required = false, defaultValue = "20") int numberOfQuestions){
        return questionService.startQuiz(categoryId, difficulty, numberOfQuestions);
    }
}
