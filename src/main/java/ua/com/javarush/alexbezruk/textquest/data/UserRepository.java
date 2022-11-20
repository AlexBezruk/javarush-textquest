package ua.com.javarush.alexbezruk.textquest.data;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getName(), user);
    }

    public User fetchByName(String name) {
        return users.get(name);
    }

    public boolean isExists(String name) {
        return users.containsKey(name);
    }
}
