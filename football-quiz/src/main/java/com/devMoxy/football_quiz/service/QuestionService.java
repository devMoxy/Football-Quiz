package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.entity.Question;
import com.devMoxy.football_quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestions(){
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByDifficulty(Difficulty difficulty){
        return questionRepository.findByDifficulty(difficulty);
    }
}
