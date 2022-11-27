package ua.com.javarush.alexbezruk.textquest.service;

import ua.com.javarush.alexbezruk.textquest.data.UserRepository;

public class InitialService {
    private final UserRepository userRepository;

    public InitialService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
