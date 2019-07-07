package enums;

import lombok.Getter;

@Getter
public enum WodCardioExerciseEnum {

    RUN("Бег"),
    BURPEE("Бурпи"),
    JUMPING_JACK("Прыгающий джек");


    private String exerciseName;

    WodCardioExerciseEnum(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
