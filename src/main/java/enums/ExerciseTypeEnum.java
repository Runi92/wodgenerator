package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExerciseTypeEnum {

    CARDIO("Кардио упражнение"),
    GYMNASTIC("Гимнастическое упражнение"),
    WEIGHTLIFTING("Тяжелоатлетическое упражнение");

    private String exerciseTypeName;
}
