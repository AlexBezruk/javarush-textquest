package ua.com.javarush.alexbezruk.textquest.data;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Question {
    String text;

    @Builder.Default
    List<Answer> answers = List.of();
    boolean isWin;
    boolean isLoose;
}

