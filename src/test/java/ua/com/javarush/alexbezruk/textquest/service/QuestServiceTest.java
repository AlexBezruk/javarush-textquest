package ua.com.javarush.alexbezruk.textquest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.alexbezruk.textquest.data.Answer;
import ua.com.javarush.alexbezruk.textquest.data.Question;
import ua.com.javarush.alexbezruk.textquest.data.User;
import ua.com.javarush.alexbezruk.textquest.data.UserRepository;
import ua.com.javarush.alexbezruk.textquest.service.exception.GameException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestServiceTest {
    private QuestService questService;

    @Mock
    UserRepository userRepository;

    @Mock
    Question currentQuestion;

    @Mock
    Question nextQuestion;

    @Mock
    List<Answer> answers;

    @Mock
    Answer answer;

    @Mock
    User user;

    @BeforeEach
    void setup() {
        questService = new QuestService(userRepository);
    }

    @Test
    void test_nextQuestion_currentQuestionIsNull() {
        assertThrows(GameException.class, () -> questService.nextQuestion(null, "0"));
    }

    @Test
    void test_nextQuestion_numberAnswerIsNull() {
        assertThrows(GameException.class, () -> questService.nextQuestion(currentQuestion, null));
    }

    @Test
    void test_nextQuestion_currentQuestionAndNumberAnswerIsNull() {
        assertThrows(GameException.class, () -> questService.nextQuestion(null, null));
    }

    @Test
    void test_nextQuestion_numberAnswerIsNotNumber() {
        assertThrows(GameException.class, () -> questService.nextQuestion(currentQuestion, "a"));
    }

    @Test
    void test_nextQuestion_numberAnswerLessZero() {
        assertThrows(GameException.class, () -> questService.nextQuestion(currentQuestion, "-1"));
    }

    @Test
    void test_nextQuestion_numberAnswerEqualsNumberOfResponses() {
        when(currentQuestion.getAnswers()).thenReturn(answers);
        when(answers.size()).thenReturn(3);

        assertThrows(GameException.class, () -> questService.nextQuestion(currentQuestion, "3"));
    }

    @Test
    void test_nextQuestion_numberAnswerMoreNumberOfResponses() {
        when(currentQuestion.getAnswers()).thenReturn(answers);
        when(answers.size()).thenReturn(3);

        assertThrows(GameException.class, () -> questService.nextQuestion(currentQuestion, "4"));
    }

    @Test
    void test_nextQuestion() {
        when(currentQuestion.getAnswers()).thenReturn(answers);
        when(answers.size()).thenReturn(3);
        when(answers.get(0)).thenReturn(answer);
        when(answer.getNextQuestion()).thenReturn(nextQuestion);

        assertEquals(nextQuestion, questService.nextQuestion(currentQuestion, "0"));
    }

    @Test
    void test_upgradeStatistics_nameIsNull() {
        assertThrows(GameException.class, () -> questService.upgradeStatistics(null));
    }

    @Test
    void test_upgradeStatistics() {
        when(userRepository.fetchByName("Sasha")).thenReturn(user);
        when(user.getGamesNumber()).thenReturn(1);

        assertEquals(2, questService.upgradeStatistics("Sasha"));
        verify(user).setGamesNumber(2);
        verify(userRepository, times(1)).save(user);
    }
}