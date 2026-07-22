package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.QuestionCreateDTO;
import com.devMoxy.football_quiz.dto.QuestionDTO;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.service.QuestionService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/api/questions")
    public QuestionDTO postQuestions(@RequestBody QuestionCreateDTO dto){
        return questionService.createQuestion(dto);
    }

}
