package ua.com.javarush.alexbezruk.textquest.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    private Question question;

    @Mock
    List<Answer> answers;


    @BeforeEach
    void setup() {
        question = Question.builder()
                .text("1")
                .img("2.jpg")
                .answers(answers)
                .isWin(false)
                .isLoose(false)
                .build();
    }

    @Test
    void test_getText() {
        String expected = "1";
        String actual = question.getText();

        assertEquals(expected, actual);
    }

    @Test
    void test_getImg() {
        String expected = "2.jpg";
        String actual = question.getImg();

        assertEquals(expected, actual);
    }

    @Test
    void test_getAnswers() {
        List<Answer> expected = answers;
        List<Answer> actual = question.getAnswers();

        assertEquals(expected, actual);
    }

    @Test
    void test_isWin() {
        boolean expected = false;
        boolean actual = question.isWin();

        assertEquals(expected, actual);
    }

    @Test
    void test_isLoose() {
        boolean expected = false;
        boolean actual = question.isLoose();

        assertEquals(expected, actual);
    }
}
