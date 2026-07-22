package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.QuestionDTO;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.entity.Question;
import com.devMoxy.football_quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public List<QuestionDTO> getAllQuestions(){
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> dtoList = new ArrayList<>();

        for (Question q : questions) {
            QuestionDTO dto = new QuestionDTO();

            dto.setId(q.getId());
            dto.setText(q.getText());
            dto.setOptionA(q.getOptionA());
            dto.setOptionB(q.getOptionB());
            dto.setOptionC(q.getOptionC());
            dto.setOptionD(q.getOptionD());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<Question> getQuestionsByDifficulty(Difficulty difficulty){
        return questionRepository.findByDifficulty(difficulty);
    }

    private QuestionDTO convertToDto(Question question){
        QuestionDTO dto  = new QuestionDTO();
        dto.setId(question.getId());
        dto.setText(question.getText());
        dto.setOptionA(question.getOptionA());
        dto.setOptionB(question.getOptionB());
        dto.setOptionC(question.getOptionC());
        dto.setOptionD(question.getOptionD());
        dto.setCategoryName(question.getCategory().getName());
        return dto;
    }
}
