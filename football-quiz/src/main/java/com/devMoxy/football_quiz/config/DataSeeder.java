package com.devMoxy.football_quiz.config;

import com.devMoxy.football_quiz.repository.CategoryRepository;
import com.devMoxy.football_quiz.repository.QuestionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.devMoxy.football_quiz.entity.Category;
import com.devMoxy.football_quiz.entity.Question;
import com.devMoxy.football_quiz.entity.Difficulty;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;

    public DataSeeder(CategoryRepository categoryRepository, QuestionRepository questionRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
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
    }
}