package ua.com.javarush.alexbezruk.textquest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.repository.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

public class InitialService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InitialService.class);
    private final UserRepository userRepository;

    public InitialService(UserRepository userRepository) {
        this.userRepository = userRepository;
        LOGGER.info("create initialService");
    }

    public User initOrCreateUser(String name) {
        if (name == null) {
            LOGGER.error("name is null");
            throw new GameException("name is null");
        }

        User user;
        if (userRepository.isExists(name)) {
            user = userRepository.fetchByName(name);
        } else {
            user = new User(name, 0);
            LOGGER.info("create new user");
            userRepository.save(user);
        }

        LOGGER.debug("initOrCreateUser({}) return new user", name);
        return user;
    }
}
