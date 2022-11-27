package ua.com.javarush.alexbezruk.textquest.service;

import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

public class InitialService {
    private final UserRepository userRepository;

    public InitialService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User initOrCreateUser(String name) {
        if (name == null) {
            throw new GameException("name is null");
        }

        User user;
        if (userRepository.isExists(name)) {
            user = userRepository.fetchByName(name);
        } else {
            user = new User(name, 0);
            userRepository.save(user);
        }

        return user;
    }
}
