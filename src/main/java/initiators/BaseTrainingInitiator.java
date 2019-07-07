package initiators;

import entities.MetconEntity;
import enums.*;
import exceptions.UnsupportedMeasurementUnitException;
import utils.InitiatorUtils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BaseTrainingInitiator {

    /**
     * Конструктор по-умолчанию
     */
    private BaseTrainingInitiator() {
    }

    /**
     * Метоод инициализации типа основной тренировки
     * @param countOfExercises количество упражнений в основоной тренировке
     * @param baseTrainingSchemaEnums список базовых схем в основной тренировке
     * @return тип основной тренировки
     */
    public static TrainingTypeEnum initTrainingType(int countOfExercises, List<BaseTrainingSchemaEnum> baseTrainingSchemaEnums) {
        TrainingTypeEnum baseTrainingType;
        if (countOfExercises == 1) {
            if (baseTrainingSchemaEnums.get(0).equals(BaseTrainingSchemaEnum.C)) {
                return TrainingTypeEnum.CARDIO_TRAIN;
            } else if (baseTrainingSchemaEnums.get(0).equals(BaseTrainingSchemaEnum.G)) {
                return TrainingTypeEnum.GYMNASTIC_SKILL_TRAIN;
            } else {
                return TrainingTypeEnum.POWERLIFTING_TRAIN;
            }
        } else if (countOfExercises == 2) {
            int numberOfTrainingType = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getBaseTrainingType().length);
            baseTrainingType = (TrainingTypeEnum) InitiatorUtils.getBaseTrainingType()[numberOfTrainingType];
            return baseTrainingType;
        } else {
            return TrainingTypeEnum.AMRAP;
        }
    }

    /**
     * Метод инициализации списка упражнений
     * @param baseTrainingSchemaEnums список базовых схем тренировок
     * @return список упражнений
     */
    public static List<ExerciseEnum> initExercises(List<BaseTrainingSchemaEnum> baseTrainingSchemaEnums) {
        List<ExerciseEnum> exercisesList = new ArrayList<>();
        if (baseTrainingSchemaEnums.size() == 1) {
            exercisesList = initExercisesForOneExerciseBaseTraining(baseTrainingSchemaEnums);
        } else if (baseTrainingSchemaEnums.size() == 2) {
            exercisesList = initExercisesForTwoExerciseBaseTraining(baseTrainingSchemaEnums);
        } else if (baseTrainingSchemaEnums.size() == 3) {
            exercisesList = initExercisesForThreeExerciseBaseTraining(baseTrainingSchemaEnums);
        }
        return exercisesList;
    }

    /**
     * Метод инициализации описка упражнений для базовой тренировки с одним упражнением
     * @param baseTrainingSchemaEnums список базовых схем тренировок
     * @return список упражнений
     */
    private static List<ExerciseEnum> initExercisesForOneExerciseBaseTraining(List<BaseTrainingSchemaEnum> baseTrainingSchemaEnums) {
        List<ExerciseEnum> exerciseEnums = new ArrayList<>();
        if (baseTrainingSchemaEnums.get(0).equals(BaseTrainingSchemaEnum.C)) {
            int numberOfCardioExercise = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getLongCardioExercisesForBaseTraining().length);
            exerciseEnums.add((ExerciseEnum) InitiatorUtils.getLongCardioExercisesForBaseTraining()[numberOfCardioExercise]);
        } else if (baseTrainingSchemaEnums.get(0).equals(BaseTrainingSchemaEnum.G)) {
            int numberOfGymnasticExercise = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getLongGymnasticExercisesForBaseTraining().length);
            exerciseEnums.add((ExerciseEnum) InitiatorUtils.getLongGymnasticExercisesForBaseTraining()[numberOfGymnasticExercise]);
        } else if (baseTrainingSchemaEnums.get(0).equals(BaseTrainingSchemaEnum.W)) {
            int numberOfWeightLiftingExercise = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getLongWeightLiftingExercisesForBaseTraining().length);
            exerciseEnums.add((ExerciseEnum) InitiatorUtils.getLongWeightLiftingExercisesForBaseTraining()[numberOfWeightLiftingExercise]);
        }
        return exerciseEnums;
    }

    /**
     * Метод инициализации списка упражнений для базовой тренировки с двумя упражнениями
     * @param baseTrainingSchema список базовых схем тренировок
     * @return список упражнений
     */
    private static List<ExerciseEnum> initExercisesForTwoExerciseBaseTraining(List<BaseTrainingSchemaEnum> baseTrainingSchema) {
        List<ExerciseEnum> exerciseEnums = new ArrayList<>();
        if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.G) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.W)) {
            if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.G)) {
                exerciseEnums.add(getGymnasticExerciseForBaseTraining());
                exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
            } else {
                exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
                exerciseEnums.add(getGymnasticExerciseForBaseTraining());
            }
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.C) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.G)) {
            if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.C)) {
                exerciseEnums.add(getCardioExerciseForBaseTraining());
                exerciseEnums.add(getGymnasticExerciseForBaseTraining());
            } else {
                exerciseEnums.add(getGymnasticExerciseForBaseTraining());
                exerciseEnums.add(getCardioExerciseForBaseTraining());
            }
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.W) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.C)) {
            if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.C)) {
                exerciseEnums.add(getCardioExerciseForBaseTraining());
                exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
            } else {
                exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
                exerciseEnums.add(getCardioExerciseForBaseTraining());
            }
        }
        return exerciseEnums;
    }

    /**
     * Метод получения кардио упражнения для базовой тренировки
     * @return кардио упражнение
     */
    private static ExerciseEnum getCardioExerciseForBaseTraining() {
        int numberOfCardioExercise = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getCardioExercisesForBaseTraining().length);
        return (ExerciseEnum) InitiatorUtils.getCardioExercisesForBaseTraining()[numberOfCardioExercise];
    }

    /**
     * Метод получения гимнастического упражнения для базовой тренировки
     * @return гимнастическое упражнение
     */
    private static ExerciseEnum getGymnasticExerciseForBaseTraining() {
        int numberOfGymnasticExercise = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getGymnasticExercisesForBaseTraining().length);
        return (ExerciseEnum) InitiatorUtils.getGymnasticExercisesForBaseTraining()[numberOfGymnasticExercise];
    }

    /**
     * Метод получения тяжелоатлетического упражнения для базовой тренировки
     * @return тяжелоатлетическое упражнение
     */
    private static ExerciseEnum getWeightLiftingExerciseForBaseTraining() {
        int numberOfWeightLiftingExercise = ThreadLocalRandom.current().nextInt(0, InitiatorUtils.getWeightLiftingExercieseForBaseTraining().length);
        return (ExerciseEnum) InitiatorUtils.getWeightLiftingExercieseForBaseTraining()[numberOfWeightLiftingExercise];
    }

    /**
     * Метод инициализации упражнений для базовой тренировки с тремя упражнениями
     * @param baseTrainingSchema список базовых схем тренировок
     * @return список упражнений
     */
    private static List<ExerciseEnum> initExercisesForThreeExerciseBaseTraining(List<BaseTrainingSchemaEnum> baseTrainingSchema) {
        List<ExerciseEnum> exerciseEnums = new ArrayList<>();
        if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.C) && baseTrainingSchema.get(1).equals(BaseTrainingSchemaEnum.G)) {
            exerciseEnums.add(getCardioExerciseForBaseTraining());
            exerciseEnums.add(getGymnasticExerciseForBaseTraining());
            exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
        } else if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.C) && baseTrainingSchema.get(1).equals(BaseTrainingSchemaEnum.W)) {
            exerciseEnums.add(getCardioExerciseForBaseTraining());
            exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
            exerciseEnums.add(getGymnasticExerciseForBaseTraining());
        } else if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.G) && baseTrainingSchema.get(1).equals(BaseTrainingSchemaEnum.W)) {
            exerciseEnums.add(getGymnasticExerciseForBaseTraining());
            exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
            exerciseEnums.add(getCardioExerciseForBaseTraining());
        } else if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.G) && baseTrainingSchema.get(1).equals(BaseTrainingSchemaEnum.C)) {
            exerciseEnums.add(getGymnasticExerciseForBaseTraining());
            exerciseEnums.add(getCardioExerciseForBaseTraining());
            exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
        } else if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.W) && baseTrainingSchema.get(1).equals(BaseTrainingSchemaEnum.C)) {
            exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
            exerciseEnums.add(getCardioExerciseForBaseTraining());
            exerciseEnums.add(getGymnasticExerciseForBaseTraining());
        } else if (baseTrainingSchema.get(0).equals(BaseTrainingSchemaEnum.W) && baseTrainingSchema.get(1).equals(BaseTrainingSchemaEnum.G)) {
            exerciseEnums.add(getWeightLiftingExerciseForBaseTraining());
            exerciseEnums.add(getGymnasticExerciseForBaseTraining());
            exerciseEnums.add(getCardioExerciseForBaseTraining());
        }
        return exerciseEnums;
    }

    /**
     * Метод инициализации меткона для базовой тренировки
     * @param exercises список упражнений
     * @param baseTrainingType тип базовой тренировки
     * @return сущность меткона базовой тренировки
     */
    public static MetconEntity initMetcon(List<ExerciseEnum> exercises, TrainingTypeEnum baseTrainingType) throws UnsupportedMeasurementUnitException {
        Map<String, String> baseTrainingMetconPlan = getBaseTrainingMetconPlan(exercises, baseTrainingType);
        String duration = getDuration(baseTrainingType);
        return MetconEntity.builder().metconTrainingType(baseTrainingType).duration(duration).metconMap(baseTrainingMetconPlan).build();
    }

    /**
     * Метод получения плана меткона базовой тренировки
     * @param exercises список упражнений базовой тренировки
     * @param baseTrainingType тип базовой тренировки
     * @return план базовой тренировки
     */
    private static Map<String, String> getBaseTrainingMetconPlan(List<ExerciseEnum> exercises, TrainingTypeEnum baseTrainingType) throws UnsupportedMeasurementUnitException {
        Map<String, String> baseTrainingMetconPlan;
        if (baseTrainingType.equals(TrainingTypeEnum.GYMNASTIC_SKILL_TRAIN) || baseTrainingType.equals(TrainingTypeEnum.POWERLIFTING_TRAIN) || baseTrainingType.equals(TrainingTypeEnum.CARDIO_TRAIN)) {
            baseTrainingMetconPlan = getBaseTrainingMetconPlanForLongTraining(exercises, baseTrainingType);
        } else {
            baseTrainingMetconPlan = InitiatorUtils.getDefaultMetconPlan(exercises);
        }
        return baseTrainingMetconPlan;
    }

    /**
     * Метод получения меткона длинной тренировки с одним упражнением
     * @param exercises список упражнений
     * @return план базовой тренировки
     */
    private static Map<String, String> getBaseTrainingMetconPlanForLongTraining(List<ExerciseEnum> exercises, TrainingTypeEnum baseTrainingType) {
        Map<String, String> baseTrainingMetconPlan = new HashMap<>();
        ExerciseEnum exercise = exercises.get(0);
        String duration = String.valueOf(baseTrainingType.getDurationForBaseTraining());
        baseTrainingMetconPlan.put(exercise.getExerciseName(), String.join(" ", "Отработка навыка на протяжении", duration));
        return baseTrainingMetconPlan;
    }

    /**
     * Метод получения продолжительности тренировки
     * @param baseTrainingType тип базовой тренировки
     * @return продолжительность тренирвоки
     */
    private static String getDuration(TrainingTypeEnum baseTrainingType) {
        return InitiatorUtils.getDuration(baseTrainingType);
    }
}
