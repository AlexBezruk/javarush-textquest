package ua.com.javarush.alexbezruk.textquest.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ua.com.javarush.alexbezruk.textquest.repository.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {
    private Map<String, User> users;
    private UserRepository userRepository;

    @Mock
    User user1;

    @Mock
    User user2;

    @BeforeEach
    void setup() {
        users = new HashMap<>();
        users.put("Sasha", user1);
        users.put("Pasha", user2);
        userRepository = new UserRepository(users);
    }

    @Test
    void test_save_newUser() {
        User newUser = new User("Vlad", 0);
        userRepository.save(newUser);

        assertEquals(3, users.size());
        assertEquals(newUser, users.get("Vlad"));

    }

    @Test
    void test_save_newUserIsDuplicate() {
        User newUser = new User("Sasha", 0);
        userRepository.save(newUser);

        assertEquals(2, users.size());
    }

    @Test
    void test_save_newUserIsNull() {
        assertThrows(GameException.class, () -> userRepository.save(null));
    }

    @Test
    void test_fetchByName() {
        assertEquals(user1, userRepository.fetchByName("Sasha"));
    }

    @Test
    void test_fetchByName_nameIsNull() {
        assertThrows(GameException.class, () -> userRepository.fetchByName(null));
    }


    @Test
    void test_isExists_true() {
        assertTrue(userRepository.isExists("Sasha"));
    }

    @Test
    void test_isExists_false() {
        assertFalse(userRepository.isExists("Vlad"));
    }

    @Test
    void test_isExists_nameIsNull() {
        assertThrows(GameException.class, () -> userRepository.isExists(null));
    }
}
