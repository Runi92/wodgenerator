package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TrainingTypeEnum {
    EMOM("EMOM", TrainingPartEnum.WARM_UP, false, true, 20, 0, 0, 0, 0, 0),
    EMO2M("EMO2M", TrainingPartEnum.WARM_UP, false, true, 20, 0, 0, 0, 0, 0),
    AFAP("AFAP", TrainingPartEnum.UNIVERSAL, false, false, 0, 5, 11, 0, 5, 11),
    AMRAP("AMRAP", TrainingPartEnum.UNIVERSAL, false, true, 20, 0, 0, 30, 0, 0),
    RFT("RFT", TrainingPartEnum.UNIVERSAL, false, false,0, 5, 11, 0, 5, 11),
    TABATA("TABATA", TrainingPartEnum.WARM_UP, false, true,18, 0, 0, 0, 0, 0),
    GYMNASTIC_SKILL_TRAIN("Тренировка навыка гимнастики", TrainingPartEnum.BASE_TRAINING, true, true, 0, 0, 0, 40, 0, 0),
    POWERLIFTING_TRAIN("Тяжелоатлетическая тренировка", TrainingPartEnum.BASE_TRAINING, true, true, 0, 0, 0, 40, 0, 0),
    CARDIO_TRAIN("Кардио тренировка", TrainingPartEnum.BASE_TRAINING, true, true, 00, 0, 0, 40, 0, 0);

    private String trainingTypeName;
    private TrainingPartEnum trainingPart;
    private boolean isLong;
    private boolean isForTime;
    private int durationForWarmUp;
    private int minRoundsForWarmUp;
    private int maxRoundsForWarmUp;
    private int durationForBaseTraining;
    private int minRoundsForBaseTraining;
    private int maxRoundsForBaseTraining;
}
