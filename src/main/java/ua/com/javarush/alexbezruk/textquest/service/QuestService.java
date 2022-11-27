package ua.com.javarush.alexbezruk.textquest.service;

import ua.com.javarush.alexbezruk.textquest.data.UserRepository;

public class QuestService {
    private final UserRepository userRepository;

    public QuestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
