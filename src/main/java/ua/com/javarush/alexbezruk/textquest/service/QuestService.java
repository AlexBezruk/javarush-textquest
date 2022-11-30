package ua.com.javarush.alexbezruk.textquest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

public class QuestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestService.class);
    private final UserRepository userRepository;

    public QuestService(UserRepository userRepository) {
        this.userRepository = userRepository;
        LOGGER.info("create userRepository");
    }

    public Question nextQuestion(Question currentQuestion, String numberAnswer) {
        if (currentQuestion == null) {
            LOGGER.error("currentQuestion is null");
            throw new GameException("currentQuestion is null");
        }

        if (numberAnswer == null) {
            LOGGER.error("numberAnswer is null");
            throw new GameException("numberAnswer is null");
        }

        int index;
        try {
            index = Integer.parseInt(numberAnswer);
        } catch (NumberFormatException e) {
            LOGGER.error("{} is not number", numberAnswer);
            throw new GameException("numberAnswer is not number", e);
        }

        if (index < 0) {
            LOGGER.error("response number [{}] < 0", index);
            throw new GameException("response number < 0");
        }

        if (index >= currentQuestion.getAnswers().size()) {
            LOGGER.error("response number [{}] >= answerSize {}", index, currentQuestion.getAnswers().size());
            throw new GameException("response number >= answerSize");
        }

        LOGGER.debug("nextQuestion({}, {}) return {}", currentQuestion, numberAnswer, currentQuestion.getAnswers().get(index).getNextQuestion());
        return currentQuestion.getAnswers().get(index).getNextQuestion();
    }

    public int upgradeStatistics(String name) {
        if (name == null) {
            LOGGER.error("name is null");
            throw new GameException("name is null");
        }

        User user = userRepository.fetchByName(name);
        int gamesNumber = user.getGamesNumber() + 1;
        user.setGamesNumber(gamesNumber);
        userRepository.save(user);

        LOGGER.debug("upgradeStatistics({}) return {}", name, gamesNumber);
        return gamesNumber;
    }
}
