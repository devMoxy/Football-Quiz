package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.QuestionDTO;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/api/questions")
    public List<QuestionDTO> getQuestions(@RequestParam Long categoryId, @RequestParam Difficulty difficulty){
        return questionService.getQuestionsByCategoryAndDifficulty(categoryId, difficulty);
    }
}
