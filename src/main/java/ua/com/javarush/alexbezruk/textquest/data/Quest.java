package ua.com.javarush.alexbezruk.textquest.data;

import java.util.List;

public class Quest {
    Question q1 = Question.builder()
            .text("Твою ложь разоблачили. Поражение")
            .isLoose(true)
            .build();
    Question q2 = Question.builder()
            .text("Ты не пошел на переговоры. Поражение")
            .isLoose(true)
            .build();
    Question q3 = Question.builder()
            .text("Ты отклонил вызов. Поражение")
            .isLoose(true)
            .build();
    Question q4 = Question.builder()
            .text("Тебя вернули домой. Победа!")
            .isWin(true)
            .build();
    Question q5 = Question.builder()
            .text("Ты поднялся на мостик. Ты кто?")
            .answers(List.of(
                    Answer.builder()
                            .text("Рассказать правду о себе")
                            .nextQuestion(q4)
                            .build(),
                    Answer.builder()
                            .text("Солгать о себе")
                            .nextQuestion(q1)
                            .build()))
            .build();
    Question q6 = Question.builder()
            .text("Ты принял вызов. Поднимаешься на мостик к капитану?")
            .answers(List.of(
                    Answer.builder()
                            .text("Подняться на мостик")
                            .nextQuestion(q5)
                            .build(),
                    Answer.builder()
                            .text("Отказаться подниматься на мостик")
                            .nextQuestion(q2)
                            .build()))
            .build();
    Question q7 = Question.builder()
            .text("Ты потерял память. Принять вызов НЛО?")
            .answers(List.of(
                    Answer.builder()
                            .text("Принять вызов")
                            .nextQuestion(q6)
                            .build(),
                    Answer.builder()
                            .text("Отклонить вызов")
                            .nextQuestion(q3)
                            .build()))
            .build();

    public Question getInitialQuestion() {
        return this.q7;
    }
}
