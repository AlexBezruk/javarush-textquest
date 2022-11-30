package ua.com.javarush.alexbezruk.textquest.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

import java.util.Map;

public class UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private final Map<String, User> users;

    public UserRepository(Map<String, User> users) {
        this.users = users;
        LOGGER.info("create userRepository");
    }

    public void save(User user) {
        if (user == null) {
            LOGGER.error("user is null");
            throw new GameException("user is null");
        }
        users.put(user.getName(), user);
        LOGGER.debug("users put {}", user.getName());
    }

    public User fetchByName(String name) {
        if (name == null) {
            LOGGER.error("name is null");
            throw new GameException("name is null");
        }
        LOGGER.debug("fetchByName({}) return {}", name, users.get(name));
        return users.get(name);
    }

    public boolean isExists(String name) {
        if (name == null) {
            LOGGER.error("name is null");
            throw new GameException("name is null");
        }
        LOGGER.debug("isExists({}) return user from userRepository", name);
        return users.containsKey(name);
    }
}
