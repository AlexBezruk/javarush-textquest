package ua.com.javarush.alexbezruk.textquest.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {
    private Quest quest;
    private static final String INITIAL_QUESTION = "Ты потерял память. Принять вызов НЛО?";

    @BeforeEach
    void setup() {
        quest = new Quest();
    }

    @Test
    void test_getInitialQuestion() {
        String expected = INITIAL_QUESTION;
        String actual = quest.getInitialQuestion().getText();

        assertEquals(expected, actual);
    }
}
