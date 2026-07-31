package com.devMoxy.football_quiz.config;

import com.devMoxy.football_quiz.entity.*;
import com.devMoxy.football_quiz.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final CareerPathQuestionRepository careerPathQuestionRepository;
    private final PlayerRepository playerRepository;
    private final AchievementRepository achievementRepository;
    private final PlayerAchievementRepository playerAchievementRepository;

    public DataSeeder(CategoryRepository categoryRepository, QuestionRepository questionRepository, CareerPathQuestionRepository careerPathQuestionRepository, PlayerRepository playerRepository, AchievementRepository achievementRepository,
                      PlayerAchievementRepository playerAchievementRepository) {
        this.categoryRepository = categoryRepository;
        this.questionRepository = questionRepository;
        this.careerPathQuestionRepository = careerPathQuestionRepository;
        this.playerRepository = playerRepository;
        this.achievementRepository = achievementRepository;
        this.playerAchievementRepository = playerAchievementRepository;
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

        // --- Achievement Matching seed data ---

        Player messi = new Player();
        messi.setName("Lionel Messi");
        messi.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/b/b4/Lionel_Messi_20180626.jpg");
        messi = playerRepository.save(messi);

        Player ronaldo = new Player();
        ronaldo.setName("Cristiano Ronaldo");
        ronaldo.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/8/8c/Cristiano_Ronaldo_2018.jpg");
        ronaldo = playerRepository.save(ronaldo);

        Player deBruyne = new Player();
        deBruyne.setName("Kevin De Bruyne");
        deBruyne.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/3/3f/Kevin_De_Bruyne_2018.jpg");
        deBruyne = playerRepository.save(deBruyne);

        Player haaland = new Player();
        haaland.setName("Erling Haaland");
        haaland.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/7/7e/Erling_Haaland_2023.jpg");
        haaland = playerRepository.save(haaland);

        Player mbappe = new Player();
        mbappe.setName("Kylian Mbappé");
        mbappe.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/7/7a/Kylian_Mbapp%C3%A9_2018.jpg");
        mbappe = playerRepository.save(mbappe);

        Player kante = new Player();
        kante.setName("N'Golo Kanté");
        kante.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/6/6b/N%27Golo_Kant%C3%A9_2018.jpg");
        kante = playerRepository.save(kante);

        Player giroud = new Player();
        giroud.setName("Olivier Giroud");
        giroud.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/9/9a/Olivier_Giroud_2018.jpg");
        giroud = playerRepository.save(giroud);

        Achievement premierLeague = new Achievement();
        premierLeague.setDescription("Won the Premier League");
        premierLeague.setImageUrl("https://upload.wikimedia.org/wikipedia/en/f/f2/Premier_League_Logo.svg");
        premierLeague = achievementRepository.save(premierLeague);

        Achievement championsLeague = new Achievement();
        championsLeague.setDescription("Won the UEFA Champions League");
        championsLeague.setImageUrl("https://upload.wikimedia.org/wikipedia/en/f/f5/UEFA_Champions_League.svg");
        championsLeague = achievementRepository.save(championsLeague);

        Achievement ballonDor = new Achievement();
        ballonDor.setDescription("Won the Ballon d'Or");
        ballonDor.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/6/6d/Ballon_d%27Or.png");
        ballonDor = achievementRepository.save(ballonDor);

        Achievement worldCupTest2 = new Achievement();
        worldCupTest2.setDescription("Won the FIFA World Cup");
        worldCupTest2.setImageUrl("https://upload.wikimedia.org/wikipedia/en/6/66/FIFA_World_Cup_Trophy.png");
        worldCupTest2 = achievementRepository.save(worldCupTest2);

        Achievement faCup = new Achievement();
        faCup.setDescription("Won the FA Cup");
        faCup.setImageUrl("https://upload.wikimedia.org/wikipedia/en/9/9e/FA_Cup_logo.svg");
        faCup = achievementRepository.save(faCup);

        Achievement laLiga = new Achievement();
        laLiga.setDescription("Won La Liga");
        laLiga.setImageUrl("https://upload.wikimedia.org/wikipedia/en/1/13/LaLiga_logo_2023.svg");
        laLiga = achievementRepository.save(laLiga);

// Premier League: De Bruyne, Haaland, Ronaldo, Kanté
        savePlayerAchievement(deBruyne, premierLeague);
        savePlayerAchievement(haaland, premierLeague);
        savePlayerAchievement(ronaldo, premierLeague);
        savePlayerAchievement(kante, premierLeague);

// Champions League: De Bruyne, Haaland, Ronaldo
        savePlayerAchievement(deBruyne, championsLeague);
        savePlayerAchievement(haaland, championsLeague);
        savePlayerAchievement(ronaldo, championsLeague);

// Ballon d'Or: Messi, Ronaldo
        savePlayerAchievement(messi, ballonDor);
        savePlayerAchievement(ronaldo, ballonDor);

// World Cup: Messi, Mbappé, Kanté, Giroud
        savePlayerAchievement(messi, worldCupTest2);
        savePlayerAchievement(mbappe, worldCupTest2);
        savePlayerAchievement(kante, worldCupTest2);
        savePlayerAchievement(giroud, worldCupTest2);

// FA Cup: Haaland, Giroud
        savePlayerAchievement(haaland, faCup);
        savePlayerAchievement(giroud, faCup);

// La Liga: Messi, Ronaldo
        savePlayerAchievement(messi, laLiga);
        savePlayerAchievement(ronaldo, laLiga);
    }

    private void savePlayerAchievement(Player player, Achievement achievement){
        PlayerAchievement pa = new PlayerAchievement();
        pa.setPlayer(player);
        pa.setAchievement(achievement);
        playerAchievementRepository.save(pa);
    }
}