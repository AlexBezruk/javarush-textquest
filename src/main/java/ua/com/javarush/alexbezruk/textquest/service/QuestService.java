package ua.com.javarush.alexbezruk.textquest.service;

import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

public class QuestService {
    private final UserRepository userRepository;

    public QuestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Question nextQuestion(Question currentQuestion, String numberAnswer) {
        if (currentQuestion == null) {
            throw new GameException("currentQuestion is null");
        }

        if (numberAnswer == null) {
            throw new GameException("numberAnswer is null");
        }

        int index;
        try {
            index = Integer.parseInt(numberAnswer);
        } catch (NumberFormatException e) {
            throw new GameException("numberAnswer is not number", e);
        }

        Question nextQuestion = currentQuestion.getAnswers().get(index).getNextQuestion();

        return nextQuestion;
    }

    public int upgradeStatistics(String name) {
        if (name == null) {
            throw new GameException("name is null");
        }

        User user = userRepository.fetchByName(name);
        int gamesNumber = user.getGamesNumber() + 1;
        user.setGamesNumber(gamesNumber);
        userRepository.save(user);

        return gamesNumber;
    }
}
