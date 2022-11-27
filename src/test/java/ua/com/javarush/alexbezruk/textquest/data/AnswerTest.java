package ua.com.javarush.alexbezruk.textquest.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {
    private Answer answer;

    @Mock
    Question question;

    @BeforeEach
    void setup() {
        answer = Answer.builder()
                .text("1")
                .nextQuestion(question)
                .build();
    }

    @Test
    void test_getText() {
        String expected = "1";
        String actual = answer.getText();

        assertEquals(expected, actual);
    }

    @Test
    void test_getNextQuestion() {
        Question expected = question;
        Question actual = answer.getNextQuestion();

        assertEquals(expected, actual);
    }
}
