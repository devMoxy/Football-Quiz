package com.devMoxy.football_quiz.config;

import com.devMoxy.football_quiz.entity.*;
import com.devMoxy.football_quiz.repository.CareerPathQuestionRepository;
import com.devMoxy.football_quiz.repository.CategoryRepository;
import com.devMoxy.football_quiz.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final CareerPathQuestionRepository careerPathQuestionRepository;

    public DataSeeder(CategoryRepository categoryRepository, QuestionRepository questionRepository, CareerPathQuestionRepository careerPathQuestionRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.careerPathQuestionRepository = careerPathQuestionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category generalKnowledge = new Category();
        generalKnowledge.setName("General Knowledge");
        generalKnowledge = categoryRepository.save(generalKnowledge);

        Category worldCup = new Category();
        worldCup.setName("World Cup");
        worldCup = categoryRepository.save(worldCup);

        Question q1 = new Question();
        q1.setText("Which country won the 2018 FIFA World Cup?");
        q1.setOptionA("Croatia");
        q1.setOptionB("France");
        q1.setOptionC("Belgium");
        q1.setOptionD("England");
        q1.setCorrectAnswerIndex(1);
        q1.setDifficulty(Difficulty.EASY);
        q1.setCategory(worldCup);
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setText("How many players are on a football team on the pitch (excluding substitutes)?");
        q2.setOptionA("9");
        q2.setOptionB("10");
        q2.setOptionC("11");
        q2.setOptionD("12");
        q2.setCorrectAnswerIndex(2);
        q2.setDifficulty(Difficulty.EASY);
        q2.setCategory(generalKnowledge);
        questionRepository.save(q2);

        Question q3 = new Question();
        q3.setText("Which country has won the most FIFA World Cups?");
        q3.setOptionA("Germany");
        q3.setOptionB("Argentina");
        q3.setOptionC("Italy");
        q3.setOptionD("Brazil");
        q3.setCorrectAnswerIndex(3);
        q3.setDifficulty(Difficulty.MEDIUM);
        q3.setCategory(worldCup);
        questionRepository.save(q3);

        CareerPathQuestion cristiano = new CareerPathQuestion();
        cristiano.setCorrectPlayerName("Cristiano Ronaldo");
        cristiano.setOptionA("Cristiano Ronaldo");
        cristiano.setOptionB("Lionel Messi");
        cristiano.setOptionC("Neymar");
        cristiano.setOptionD("Kylian Mbappé");
        cristiano.setCorrectAnswerIndex(0);
        cristiano.setDifficulty(Difficulty.EASY);

        ClubStint stint1 = new ClubStint();
        stint1.setClubName("Sporting CP");
        stint1.setLogoUrl("https://upload.wikimedia.org/wikipedia/en/e/e1/Sporting_Clube_de_Portugal_%28Logo%29.svg");
        stint1.setClubOrder(1);
        stint1.setCareerPathQuestion(cristiano);

        ClubStint stint2 = new ClubStint();
        stint2.setClubName("Manchester United");
        stint2.setLogoUrl("https://upload.wikimedia.org/wikipedia/en/7/7a/Manchester_United_FC_crest.svg");
        stint2.setClubOrder(2);
        stint2.setCareerPathQuestion(cristiano);

        ClubStint stint3 = new ClubStint();
        stint3.setClubName("Real Madrid");
        stint3.setLogoUrl("https://upload.wikimedia.org/wikipedia/en/5/56/Real_Madrid_CF.svg");
        stint3.setClubOrder(3);
        stint3.setCareerPathQuestion(cristiano);

        cristiano.setClubStints(List.of(stint1, stint2, stint3));
        careerPathQuestionRepository.save(cristiano);
    }
}