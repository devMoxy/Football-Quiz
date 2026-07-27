package com.devMoxy.football_quiz.controller;

import com.devMoxy.football_quiz.dto.CareerPathQuestionCreateDTO;
import com.devMoxy.football_quiz.dto.CareerPathQuestionDTO;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.service.CareerPathQuestionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CareerPathController {
    private final CareerPathQuestionService careerPathQuestionService;

    public CareerPathController(CareerPathQuestionService careerPathQuestionService) {
        this.careerPathQuestionService = careerPathQuestionService;
    }

    @GetMapping("/api/career-path")
    public List<CareerPathQuestionDTO> getCareerPathQuestions(@RequestParam Difficulty difficulty){
        return careerPathQuestionService.getCareerPathQuestionsByDifficulty(difficulty);
    }

    @PostMapping("/api/career-path")
    public CareerPathQuestionDTO postCareerPathQuestions(@Valid @RequestBody CareerPathQuestionCreateDTO dto){
        return careerPathQuestionService.createCareerPathQuestion(dto);
    }
}
