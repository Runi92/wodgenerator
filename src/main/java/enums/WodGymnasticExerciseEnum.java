package enums;

import lombok.Getter;

@Getter
public enum WodGymnasticExerciseEnum {

    STRICT_HAND_STAND_PUSH_UP("Строгие отжимания в стойке на руках"),
    AIR_SQUAD("Воздушные приседания"),
    STRICT_PULL_UP("Строние подтягивания"),
    PISTOL_SQUAT("Приседания пистолет"),
    PUSH_UP("Отжимания"),
    SIT_UP("Подъемы корпуса на пресс"),
    HAND_STAND("Подъем в стойку на руках"),
    RING_MUSCLE_UP("Выходы силой на кольцах");

    private String exerciseName;

    WodGymnasticExerciseEnum(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
