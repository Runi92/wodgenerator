package initiators;

import entities.MetconEntity;
import enums.*;
import exceptions.UnsupportedExerciseCountException;
import exceptions.UnsupportedMeasurementUnitException;
import exceptions.UnsupportedSchemaException;
import utils.InitiatorUtils;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WarmUpInitiator {

    private static final String WARM_UP_SCHEMA = "Схема разминки";
    private static final String UNSUPPORTED = "не поддерживается.";

    private WarmUpInitiator() {
    }

    public static int initExerciseCount(int baseExerciseCount) throws UnsupportedExerciseCountException {
        switch (baseExerciseCount) {
            case 1:
                return 3;
            case 2:
                return 2;
            case 3:
                return 1;
            default:
                throw new UnsupportedExerciseCountException(String.join(" ", "Ошибка при инициализации количества упражнений в разминке. В базовом меткоине должно быть от 1 до 3 упражнений. Текущее количество упраждений", String.valueOf(baseExerciseCount)));
        }
    }

    public static TrainingTypeEnum initTrainingType(int countOfExercises) {
        TrainingTypeEnum trainingType;
        int numberOfTrainingType;
        if (countOfExercises == 1) {
            do {
                numberOfTrainingType = ThreadLocalRandom.current().nextInt(0, Arrays.stream(TrainingTypeEnum.values()).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
                trainingType = (TrainingTypeEnum) Arrays.stream(TrainingTypeEnum.values()).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfTrainingType];
            } while (trainingType == TrainingTypeEnum.TABATA);
            return trainingType;
        } else {
            numberOfTrainingType = ThreadLocalRandom.current().nextInt(0, Arrays.stream(TrainingTypeEnum.values()).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
            trainingType = (TrainingTypeEnum) Arrays.stream(TrainingTypeEnum.values()).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfTrainingType];
            return trainingType;
        }
    }

    public static List<ExerciseEnum> initExercises(List<BaseTrainingSchemaEnum> baseTrainingSchemaEnums) throws UnsupportedSchemaException {
        List<ExerciseEnum> exercisesList = new ArrayList<>();
        if (baseTrainingSchemaEnums.size() == 1) {
            exercisesList = initExercisesForOneExerciseBaseTraining();
        } else if (baseTrainingSchemaEnums.size() == 2) {
            exercisesList = initExercisesForTwoExerciseBaseTraining(baseTrainingSchemaEnums);
        } else if (baseTrainingSchemaEnums.size() == 3) {
            exercisesList = initExercisesForThreeExerciseBaseTraining(baseTrainingSchemaEnums);
        }
        Collections.shuffle(exercisesList);
        return exercisesList;
    }

    private static ExerciseEnum getCardioWarmUpExercise() {
        int numberOfCardioExercise = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        return (ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfCardioExercise];
    }

    private static ExerciseEnum getGymnasticWarmUpExercise() {
        int numberOfGymnasticExercise = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        return (ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfGymnasticExercise];
    }

    private static ExerciseEnum getWeightLiftingWarmUpExercise() {
        int numberOfWeightLiftingExercise = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        return (ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfWeightLiftingExercise];
    }

    private static ExerciseEnum getWarmUpExerciseForThreeDiffBaseTrainingSchema() throws UnsupportedSchemaException {
        int numberOfWarmUpSchema = ThreadLocalRandom.current().nextInt(0, Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray().length);
        BaseTrainingSchemaEnum warmUpSchema = (BaseTrainingSchemaEnum) Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema];
        if (warmUpSchema == BaseTrainingSchemaEnum.C) {
            return getCardioWarmUpExercise();
        } else if (warmUpSchema == BaseTrainingSchemaEnum.G) {
            return getGymnasticWarmUpExercise();
        } else if (warmUpSchema == BaseTrainingSchemaEnum.W) {
            return getWeightLiftingWarmUpExercise();
        } else {
            throw new UnsupportedSchemaException(String.join(" ", WARM_UP_SCHEMA, warmUpSchema.getBaseTrainingSchemaName(), UNSUPPORTED));
        }
    }

    private static ExerciseEnum getWarmUpExerciseForThreeCardioBaseTrainingSchema() throws UnsupportedSchemaException {
        int numberOfWarmUpSchema;
        do {
            numberOfWarmUpSchema = ThreadLocalRandom.current().nextInt(0, Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray().length);
        } while (BaseTrainingSchemaEnum.C == Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema]);
        BaseTrainingSchemaEnum warmUpSchema = (BaseTrainingSchemaEnum) Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema];
        if (warmUpSchema == BaseTrainingSchemaEnum.G) {
            return getGymnasticWarmUpExercise();
        } else if (warmUpSchema == BaseTrainingSchemaEnum.W) {
            return getWeightLiftingWarmUpExercise();
        } else {
            throw new UnsupportedSchemaException(String.join(" ", WARM_UP_SCHEMA, warmUpSchema.getBaseTrainingSchemaName(), UNSUPPORTED));
        }
    }

    private static ExerciseEnum getWarmUpExerciseForThreeGymnasticBaseTrainingSchema() throws UnsupportedSchemaException {
        int numberOfWarmUpSchema;
        do {
            numberOfWarmUpSchema = ThreadLocalRandom.current().nextInt(0, Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray().length);
        } while (BaseTrainingSchemaEnum.G == Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema]);
        BaseTrainingSchemaEnum warmUpSchema = (BaseTrainingSchemaEnum) Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema];
        if (warmUpSchema == BaseTrainingSchemaEnum.C) {
            return getCardioWarmUpExercise();
        } else if (warmUpSchema == BaseTrainingSchemaEnum.W) {
            return getWeightLiftingWarmUpExercise();
        } else {
            throw new UnsupportedSchemaException(String.join(" ", WARM_UP_SCHEMA, warmUpSchema.getBaseTrainingSchemaName(), UNSUPPORTED));
        }
    }

    private static ExerciseEnum getWarmUpExerciseForThreeWeightLiftingBaseTrainingSchema() throws UnsupportedSchemaException {
        int numberOfWarmUpSchema;
        do {
            numberOfWarmUpSchema = ThreadLocalRandom.current().nextInt(0, Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray().length);
        } while (BaseTrainingSchemaEnum.W == Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema]);
        BaseTrainingSchemaEnum warmUpSchema = (BaseTrainingSchemaEnum) Arrays.stream(BaseTrainingSchemaEnum.values()).filter(BaseTrainingSchemaEnum::isTraining).toArray()[numberOfWarmUpSchema];
        if (warmUpSchema == BaseTrainingSchemaEnum.C) {
            return getCardioWarmUpExercise();
        } else if (warmUpSchema == BaseTrainingSchemaEnum.G) {
            return getGymnasticWarmUpExercise();
        } else {
            throw new UnsupportedSchemaException(String.join(" ", WARM_UP_SCHEMA, warmUpSchema.getBaseTrainingSchemaName(), UNSUPPORTED));
        }
    }

    private static List<ExerciseEnum> initExercisesForThreeExerciseBaseTraining(List<BaseTrainingSchemaEnum> baseTrainingSchema) throws UnsupportedSchemaException {
        List<ExerciseEnum> exercises = new ArrayList<>();
        if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.C) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.W) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.G)) {
            exercises.add(getWarmUpExerciseForThreeDiffBaseTrainingSchema());
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.C) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.G)) {
            exercises.add(getWeightLiftingWarmUpExercise());
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.C) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.W)) {
            exercises.add(getGymnasticWarmUpExercise());
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.G) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.W)) {
            exercises.add(getCardioWarmUpExercise());
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.C)) {
            exercises.add(getWarmUpExerciseForThreeCardioBaseTrainingSchema());
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.G)) {
            exercises.add(getWarmUpExerciseForThreeGymnasticBaseTrainingSchema());
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.W)) {
            exercises.add(getWarmUpExerciseForThreeWeightLiftingBaseTrainingSchema());
        }
        return exercises;
    }

    private static List<ExerciseEnum> getWarmUpExercisesForGymnasticAndWeightLiftingBaseTrainingSchema() {
        List<ExerciseEnum> exercises = new ArrayList<>();
        int numberOfCardioExerciseOne = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        int numberOfCardioExerciseTwo;
        do {
            numberOfCardioExerciseTwo = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        } while (numberOfCardioExerciseOne == numberOfCardioExerciseTwo);
        exercises.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfCardioExerciseOne]);
        exercises.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfCardioExerciseTwo]);
        return exercises;
    }

    private static List<ExerciseEnum> getWarmUpExercisesForCardioAndGymnasticBaseTrainingSchema() {
        List<ExerciseEnum> exercises = new ArrayList<>();
        int numberOfWeightLiftingExerciseOne = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        int numberOfWeightLiftingExerciseTwo;
        do {
            numberOfWeightLiftingExerciseTwo = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        } while (numberOfWeightLiftingExerciseOne == numberOfWeightLiftingExerciseTwo);
        exercises.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfWeightLiftingExerciseOne]);
        exercises.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfWeightLiftingExerciseTwo]);
        return exercises;
    }

    private static List<ExerciseEnum> getWarmUpExercisesForWeightLiftingAndCardioBaseTrainingSchem() {
        List<ExerciseEnum> exercises = new ArrayList<>();
        int numberOfGymnasticExerciseOne = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        int numberOfGymnasticExerciseTwo;
        do {
            numberOfGymnasticExerciseTwo = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        } while (numberOfGymnasticExerciseOne == numberOfGymnasticExerciseTwo);
        exercises.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfGymnasticExerciseOne]);
        exercises.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfGymnasticExerciseTwo]);
        return exercises;
    }

    private static List<ExerciseEnum> initExercisesForTwoExerciseBaseTraining(List<BaseTrainingSchemaEnum> baseTrainingSchema) {
        List<ExerciseEnum> exercises = new ArrayList<>();
        if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.G) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.W)) {
            for (ExerciseEnum exercise : getWarmUpExercisesForGymnasticAndWeightLiftingBaseTrainingSchema()) {
                exercises.add(exercise);
            }
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.C) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.G)) {
            for (ExerciseEnum exercise : getWarmUpExercisesForCardioAndGymnasticBaseTrainingSchema()) {
                exercises.add(exercise);
            }
        } else if (baseTrainingSchema.contains(BaseTrainingSchemaEnum.W) && baseTrainingSchema.contains(BaseTrainingSchemaEnum.C)) {
            for (ExerciseEnum exercise : getWarmUpExercisesForWeightLiftingAndCardioBaseTrainingSchem()) {
                exercises.add(exercise);
            }
        }
        return exercises;
    }

    private static List<ExerciseEnum> initExercisesForOneExerciseBaseTraining() {
        List<ExerciseEnum> exerciseEnums = new ArrayList<>();
        int numberOfGymnasticExercise = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        int numberOfCardioExercise = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        int numberOfWeightLiftingExercise = ThreadLocalRandom.current().nextInt(0, Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray().length);
        exerciseEnums.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfGymnasticExercise]);
        exerciseEnums.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfCardioExercise]);
        exerciseEnums.add((ExerciseEnum) Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.WARM_UP) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray()[numberOfWeightLiftingExercise]);
        return exerciseEnums;
    }

    public static MetconEntity initMetcon(List<ExerciseEnum> exercisesList, TrainingTypeEnum warmUpType) throws UnsupportedMeasurementUnitException {
        Map<String, String> warmUpMetconPlan = getWarmUpMetconPlan(exercisesList, warmUpType);
        String duration = getDuration(warmUpType);
        return MetconEntity.builder().metconTrainingType(warmUpType).duration(duration).metconMap(warmUpMetconPlan).build();
    }

    private static String getDuration(TrainingTypeEnum warmUpType) {
        return InitiatorUtils.getDuration(warmUpType);
    }

    private static Map<String, String> getWarmUpMetconPlan(List<ExerciseEnum> exercises, TrainingTypeEnum warmUpType) throws UnsupportedMeasurementUnitException {
        Map<String, String> warmUpMetconPlan;
        if (warmUpType.equals(TrainingTypeEnum.TABATA)) {
            warmUpMetconPlan = getWarmUpMetconPlanForTabata(exercises);
        } else {
            warmUpMetconPlan = InitiatorUtils.getDefaultMetconPlan(exercises);
        }
        return warmUpMetconPlan;
    }



    private static Map<String, String> getWarmUpMetconPlanForTabata(List<ExerciseEnum> exercisesList) {
        Map<String, String> warmUpMetconPlanForTabata = new HashMap<>();
        for (ExerciseEnum exercise : exercisesList) {
            warmUpMetconPlanForTabata.put(exercise.getExerciseName(), "");
        }
        return warmUpMetconPlanForTabata;
    }

}
