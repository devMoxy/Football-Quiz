package com.devMoxy.football_quiz.service;

import com.devMoxy.football_quiz.dto.CareerPathQuestionCreateDTO;
import com.devMoxy.football_quiz.dto.CareerPathQuestionDTO;
import com.devMoxy.football_quiz.dto.ClubStintDTO;
import com.devMoxy.football_quiz.entity.CareerPathQuestion;
import com.devMoxy.football_quiz.entity.ClubStint;
import com.devMoxy.football_quiz.entity.Difficulty;
import com.devMoxy.football_quiz.repository.CareerPathQuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareerPathQuestionService {
    private final CareerPathQuestionRepository careerPathQuestionRepository;

    public CareerPathQuestionService(CareerPathQuestionRepository careerPathQuestionRepository) {
        this.careerPathQuestionRepository = careerPathQuestionRepository;
    }

    private ClubStintDTO convertClubStintToDto(ClubStint clubStint){
        ClubStintDTO dto = new ClubStintDTO();
        dto.setClubName(clubStint.getClubName());
        dto.setLogoUrl(clubStint.getLogoUrl());
        dto.setClubOrder(clubStint.getClubOrder());
        return dto;
    }

    private CareerPathQuestionDTO convertToDto(CareerPathQuestion question){
        CareerPathQuestionDTO dto = new CareerPathQuestionDTO();
        dto.setId(question.getId());
        dto.setOptionA(question.getOptionA());
        dto.setOptionB(question.getOptionB());
        dto.setOptionC(question.getOptionC());
        dto.setOptionD(question.getOptionD());
        dto.setDifficulty(question.getDifficulty());

        List<ClubStintDTO> clubStintDtos = new ArrayList<>();
        for(ClubStint clubStint : question.getClubStints()){
            ClubStintDTO clubStintDto = convertClubStintToDto(clubStint);
            clubStintDtos.add(clubStintDto);
        }
        dto.setClubStints(clubStintDtos);

        return dto;
    }

    public List<CareerPathQuestionDTO> getAllCareerPathQuestions(){
        List<CareerPathQuestion> questions = careerPathQuestionRepository.findAll();
        List<CareerPathQuestionDTO> dtoList = new ArrayList<>();

        for(CareerPathQuestion q : questions){
            CareerPathQuestionDTO dto = convertToDto(q);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<CareerPathQuestionDTO> getCareerPathQuestionsByDifficulty(Difficulty difficulty){
        List<CareerPathQuestion> questions = careerPathQuestionRepository.findByDifficulty(difficulty);
        List<CareerPathQuestionDTO> dtoList = new ArrayList<>();

        for(CareerPathQuestion q : questions){
            CareerPathQuestionDTO dto = convertToDto(q);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public CareerPathQuestionDTO createCareerPathQuestion(CareerPathQuestionCreateDTO dto){
        CareerPathQuestion question = new CareerPathQuestion();
        question.setCorrectPlayerName(dto.getCorrectPlayerName());
        question.setOptionA(dto.getOptionA());
        question.setOptionB(dto.getOptionB());
        question.setOptionC(dto.getOptionC());
        question.setOptionD(dto.getOptionD());
        question.setCorrectAnswerIndex(dto.getCorrectAnswerIndex());
        question.setDifficulty(dto.getDifficulty());

        List<ClubStint> clubStints = new ArrayList<>();
        for (ClubStintDTO clubStintDto : dto.getClubStints()) {
            ClubStint clubStint = new ClubStint();
            clubStint.setClubName(clubStintDto.getClubName());
            clubStint.setLogoUrl(clubStintDto.getLogoUrl());
            clubStint.setClubOrder(clubStintDto.getClubOrder());
            clubStint.setCareerPathQuestion(question);
            clubStints.add(clubStint);
        }
        question.setClubStints(clubStints);
        CareerPathQuestion saved = careerPathQuestionRepository.save(question);
        return convertToDto(saved);
    }
}