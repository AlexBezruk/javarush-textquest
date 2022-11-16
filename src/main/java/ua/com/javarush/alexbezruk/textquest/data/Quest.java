package ua.com.javarush.alexbezruk.textquest.data;

import java.util.List;

public class Quest {
    Question q1 = Question.builder()
            .text("Тебя разоблачили. Ты проиграл!")
            .isLoose(true)
            .build();
    Question q2 = Question.builder()
            .text("Ты не пошел на переговоры. Это провал и поражение!")
            .isLoose(true)
            .build();
    Question q3 = Question.builder()
            .text("Твою ложи разоблачили. Прощай!")
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
                            .text("Солгать о себе")
                            .nextQuestion(this.q3)
                            .build(),
                    Answer.builder()
                            .text("Рассказать правду о себе")
                            .nextQuestion(this.q4)
                            .build()))
            .build();
    Question q6 = Question.builder()
            .text("Ты принял вызов. Поднимаешься на мостик к капитану?")
            .answers(List.of(
                    Answer.builder()
                            .text("Отказаться")
                            .nextQuestion(this.q2)
                            .build(),
                    Answer.builder()
                            .text("Подняться")
                            .nextQuestion(this.q5)
                            .build()))
            .build();
    ;
    Question q7 = Question.builder()
            .text("Ты потерял память. Принять вызов НЛО?")
            .answers(List.of(
                    Answer.builder()
                            .text("Отклонить вызов")
                            .nextQuestion(this.q1)
                            .build(),
                    Answer.builder()
                            .text("Принять вызов")
                            .nextQuestion(this.q6)
                            .build()))
            .build();

    public Question getInitialQuestion() {
        return this.q7;
    }
}

