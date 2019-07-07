package enums;

import lombok.Getter;

@Getter
public enum WodWeightLiftingExerciseEnum {

    POWER_SNATCH("Силовой рывок в стойку"),
    WALL_BALL("Броски мяча в стену"),
    DUMBBELL_THRUSTERS("Трастеры гантелями"),
    CLEAN_AND_JERK("Взятие на грудь и толчок штанги"),
    BENCH_PRESS("Жим штанги лежа"),
    SNATCH("Рывок штанги в сед"),
    HANG_CLEAN("Взятие штанги на грудь с виса");


    private String exerciseName;

    WodWeightLiftingExerciseEnum(String exerciseName) {
        this.exerciseName = exerciseName;
    }


}
