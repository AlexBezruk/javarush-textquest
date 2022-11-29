package ua.com.javarush.alexbezruk.textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InitialServiceTest {
    private InitialService initialService;

    @Mock
    UserRepository userRepository;

    @Mock
    User user;

    @BeforeEach
    void setup() {
        initialService = new InitialService(userRepository);
    }

    @Test
    void test_initOrCreateUser_nameIsNull() {
        assertThrows(GameException.class, () -> initialService.initOrCreateUser(null));
    }

    @Test
    void test_initOrCreateUser_nameIsExists() {
        when(userRepository.isExists(eq("Sasha"))).thenReturn(true);
        when(userRepository.fetchByName(eq("Sasha"))).thenReturn(user);

        assertEquals(user, initialService.initOrCreateUser("Sasha"));
    }

    @Test
    void test_initOrCreateUser_nameIsNotExists() {
        User newUser = new User("Sasha", 0);
        when(userRepository.isExists(eq("Sasha"))).thenReturn(false);

        assertEquals(newUser, initialService.initOrCreateUser("Sasha"));
        verify(userRepository, times(1)).save(newUser);
    }
}