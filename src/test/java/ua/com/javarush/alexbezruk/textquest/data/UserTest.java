package ua.com.javarush.alexbezruk.textquest.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setup() {
        user = new User("Sasha", 100);
    }

    @Test
    void test_getName() {
        String expected = "Sasha";
        String actual = user.getName();

        assertEquals(expected, actual);
    }

    @Test
    void test_getGamesNumber() {
        int expected = 100;
        int actual = user.getGamesNumber();

        assertEquals(expected, actual);
    }

    @Test
    void test_setName() {
        String expected = "Vlad";
        user.setName("Vlad");

        assertEquals(expected, user.getName());
    }

    @Test
    void test_setGamesNumber() {
        int expected = 1;
        user.setGamesNumber(1);

        assertEquals(expected, user.getGamesNumber());
    }
}
