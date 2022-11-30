package ua.com.javarush.alexbezruk.textquest.data;

import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

import java.util.Map;

public class UserRepository {
    private final Map<String, User> users;

    public UserRepository(Map<String, User> users) {
        this.users = users;
    }

    public void save(User user) {
        if (user == null) {
            throw new GameException("User is null");
        }
        users.put(user.getName(), user);
    }

    public User fetchByName(String name) {
        if (name == null) {
            throw new GameException("name is null");
        }
        return users.get(name);
    }

    public boolean isExists(String name) {
        if (name == null) {
            throw new GameException("name is null");
        }
        return users.containsKey(name);
    }
}
