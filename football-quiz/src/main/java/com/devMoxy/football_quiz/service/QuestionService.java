package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.QuestionCreateDTO;
import com.devMoxy.football_quiz.dto.QuestionDTO;
import com.devMoxy.football_quiz.entity.Category;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.entity.Question;
import com.devMoxy.football_quiz.repository.CategoryRepository;
import com.devMoxy.football_quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    public QuestionService(QuestionRepository questionRepository, CategoryRepository categoryRepository){
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<QuestionDTO> getAllQuestions(){
        List<Question> questions = questionRepository.findAll();
        List<QuestionDTO> dtoList = new ArrayList<>();

        for (Question q : questions) {
            QuestionDTO dto = convertToDto(q);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<QuestionDTO> getQuestionsByDifficulty(Difficulty difficulty){
        List<Question> questions = questionRepository.findByDifficulty(difficulty);
        List<QuestionDTO> dtoList = new ArrayList<>();

        for(Question q: questions){
            QuestionDTO dto = convertToDto(q);
            dtoList.add(dto);
        }
        return dtoList;
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
        dto.setDifficulty(question.getDifficulty());
        return dto;
    }

    public List<QuestionDTO> getQuestionsByCategoryAndDifficulty(Long categoryId, Difficulty difficulty){
        List<Question> questions = questionRepository.findByDifficultyAndCategory_Id(difficulty, categoryId);
        List<QuestionDTO> dtoList = new ArrayList<>();

        for(Question q: questions){
            QuestionDTO dto = convertToDto(q);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public QuestionDTO createQuestion(QuestionCreateDTO dto){
        Category category = categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        Question questions = new Question();
        questions.setCategory(category);
        questions.setText(dto.getText());
        questions.setOptionA(dto.getOptionA());
        questions.setOptionB(dto.getOptionB());
        questions.setOptionC(dto.getOptionC());
        questions.setOptionD(dto.getOptionD());
        questions.setDifficulty(dto.getDifficulty());
        questions.setCorrectAnswerIndex(dto.getCorrectAnswerIndex());
        Question saved = questionRepository.save(questions);
        return convertToDto(saved);
    }

    public List<QuestionDTO> startQuiz(Long categoryId, Difficulty difficulty, int numberOfQuestion){
        List<QuestionDTO> dtoList = getQuestionsByCategoryAndDifficulty(categoryId, difficulty);
        Collections.shuffle(dtoList);
        int limit = Math.min(numberOfQuestion, dtoList.size());
        List<QuestionDTO> selectedQuestions = dtoList.subList(0, limit);
        return selectedQuestions;
    }
}
