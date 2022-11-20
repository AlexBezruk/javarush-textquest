package ua.com.javarush.alexbezruk.textquest.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private int gamesNumber;
}
