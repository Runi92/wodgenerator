package enums;

import lombok.Getter;

@Getter
public enum TrainingPartEnum {

    WARM_UP("Разминка"),
    BASE_TRAINING("Основная тренировка"),
    UNIVERSAL("Универсальная");

    private String trainingPartName;

    TrainingPartEnum(String trainingPartName) {
        this.trainingPartName = trainingPartName;
    }

}
