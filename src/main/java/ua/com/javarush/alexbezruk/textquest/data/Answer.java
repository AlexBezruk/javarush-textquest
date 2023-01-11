package ua.com.javarush.alexbezruk.textquest.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Answer {
    private String text;
    private Question nextQuestion;
}
