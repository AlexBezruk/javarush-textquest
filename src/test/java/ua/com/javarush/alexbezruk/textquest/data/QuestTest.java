package ua.com.javarush.alexbezruk.textquest.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {
    private Quest quest;

    @BeforeEach
    void setup() {
        quest = new Quest();
    }

    @Test
    void getInitialQuestion() {
        String expected = "Ты потерял память. Принять вызов НЛО?";
        String actual = quest.getInitialQuestion().getText();

        assertEquals(expected, actual);
    }
}