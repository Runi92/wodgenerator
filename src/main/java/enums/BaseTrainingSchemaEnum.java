package enums;

import lombok.Getter;

@Getter
public enum BaseTrainingSchemaEnum {
    R("Отдых", false),
    C("Кардио тренировка", true),
    W("Тяжелоатлетическая тренировка", true),
    G("Гимнастическая тренировка", true);

    private String baseTrainingSchemaName;
    private boolean isTraining;

    BaseTrainingSchemaEnum(String baseTrainingSchemaName, boolean isTraining) {
        this.baseTrainingSchemaName = baseTrainingSchemaName;
        this.isTraining = isTraining;
    }

}
