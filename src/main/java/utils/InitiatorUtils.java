package utils;

import enums.*;
import exceptions.UnsupportedMeasurementUnitException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class InitiatorUtils {

    private static final String VALUE = "value";
    private static final String MEASUREMENT_UNIT = "measurementUnit";
    private static final String WEIGHT = "weight";
    private static final String MIN_VALUE = "minValue";
    private static final String MAX_VALUE = "maxValue";
    private static final String MINUTES = "минут";
    private static final String ROUNDS = "кругов";



    public static String getDuration(TrainingTypeEnum trainingType) {
        if (trainingType.isForTime()) {
            return String.join(" ", String.valueOf(trainingType.getDurationForWarmUp()), MINUTES);
        } else {
            return String.join(" ", String.valueOf(ThreadLocalRandom.current().nextInt(trainingType.getMinRoundsForWarmUp(), trainingType.getMaxRoundsForWarmUp())), ROUNDS);
        }
    }

    public static String getWeightForWeightLifting(String reps, ExerciseEnum exercise) {
        int repsCount = Integer.parseInt(reps);
        if (exercise == ExerciseEnum.WALL_BALL) {
            return "9 Kg";
        } else if (exercise == ExerciseEnum.KETTLEBELL_SWING) {
            if (repsCount <= 6) {
                return "32 Kg";
            } else if (repsCount <= 10) {
                return "24 Kg";
            } else {
                return "16 Kg";
            }
        } else {
            if (repsCount <= 5) {
                return "80%";
            } else if (repsCount == 6) {
                return "75%";
            } else if (repsCount == 7) {
                return "70%";
            } else if (repsCount == 8) {
                return "65%";
            } else if (repsCount == 9) {
                return "60%";
            } else {
                return "50%";
            }
        }
    }

    /**
     * Метод получения количественных характеристик упражнения в соответствии с единицей измерения
     * @param exercise упражнение
     * @return соотношение единиц измерения к их количественным характеристикам
     * @throws UnsupportedMeasurementUnitException
     */
    public static Map<MeasurementUnitEnum, Map<String, Integer>> getExerciseValues(ExerciseEnum exercise) throws UnsupportedMeasurementUnitException {
        Map<MeasurementUnitEnum, Map<String, Integer>> values = new EnumMap<>(MeasurementUnitEnum.class);
        int minValue;
        int maxValue;
        for (MeasurementUnitEnum measurementUnit : exercise.getMeasurementUnits()) {
            if (measurementUnit.equals(MeasurementUnitEnum.CAL)) {
                Map<String, Integer> minMaxValues = new HashMap<>();
                minValue = exercise.getMinMaxCalValuesForWarmUp().get(0);
                maxValue = exercise.getMinMaxCalValuesForWarmUp().get(1);
                minMaxValues.put(MIN_VALUE, minValue);
                minMaxValues.put(MAX_VALUE, maxValue);
                values.put(measurementUnit, minMaxValues);
            } else if (measurementUnit.equals(MeasurementUnitEnum.REP)) {
                Map<String, Integer> minMaxValues = new HashMap<>();
                minValue = exercise.getMinMaxRepValuesForWarmUp().get(0);
                maxValue = exercise.getMinMaxRepValuesForWarmUp().get(1);
                minMaxValues.put(MIN_VALUE, minValue);
                minMaxValues.put(MAX_VALUE, maxValue);
                values.put(measurementUnit, minMaxValues);
            } else if (measurementUnit.equals(MeasurementUnitEnum.SEC)) {
                Map<String, Integer> minMaxValues = new HashMap<>();
                minValue = exercise.getMinMaxSecValuesForWarmUp().get(0);
                maxValue = exercise.getMinMaxSecValuesForWarmUp().get(1);
                minMaxValues.put(MIN_VALUE, minValue);
                minMaxValues.put(MAX_VALUE, maxValue);
                values.put(measurementUnit, minMaxValues);
            } else if (measurementUnit.equals(MeasurementUnitEnum.METTERS)) {
                Map<String, Integer> minMaxValues = new HashMap<>();
                minValue = exercise.getMinMaxMetterValuesForWarmUp().get(0);
                maxValue = exercise.getMinMaxMetterValuesForWarmUp().get(1);
                minMaxValues.put(MIN_VALUE, minValue);
                minMaxValues.put(MAX_VALUE, maxValue);
                values.put(measurementUnit, minMaxValues);
            } else {
                throw new UnsupportedMeasurementUnitException(String.join(" ", "Единица измерения", measurementUnit.getMeasurementUnitName(), "не поддерживатся"));
            }
        }
        return values;
    }

    /**
     * Метод получения единицы измерения упражнения
     * @param exercise упражнение
     * @return единица измерения
     */
    public static MeasurementUnitEnum getMeasurementUnitEnum(ExerciseEnum exercise) {
        return exercise.getMeasurementUnits().get(ThreadLocalRandom.current().nextInt(exercise.getMeasurementUnits().size()));
    }


    /**
     * Метод получения плана меткона тренировки по-умолчанию
     * @param exercises список упражнений
     * @return план тренирвоки
     * @throws UnsupportedMeasurementUnitException
     */
    public static Map<String, String> getDefaultMetconPlan(List<ExerciseEnum> exercises) throws UnsupportedMeasurementUnitException {
        Map<String, String> warmUpMetconPlan = new HashMap<>();
        Map<String, String> valueMeasurementUnitWeight;

        for (ExerciseEnum exercise : exercises) {
            MeasurementUnitEnum measurementUnitEnum = getMeasurementUnitEnum(exercise);
            Map<MeasurementUnitEnum, Map<String, Integer>> values = getExerciseValues(exercise);
            valueMeasurementUnitWeight = InitiatorUtils.getValueMeasurementUnitWeight(exercise, values, measurementUnitEnum);
            warmUpMetconPlan.put(exercise.getExerciseName(), String.join(" ", valueMeasurementUnitWeight.get(VALUE), valueMeasurementUnitWeight.get(MEASUREMENT_UNIT), valueMeasurementUnitWeight.get(WEIGHT)));
        }
        return warmUpMetconPlan;
    }

    /**
     * Метод получения количественных характеристик, единиц измерения и веса
     * @param exercise упражнение
     * @param values соотношение единиц измерения к их количественным характеристикам
     * @param measurementUnitEnum единица измерения
     * @return
     */
    public static Map<String, String> getValueMeasurementUnitWeight(ExerciseEnum exercise, Map<MeasurementUnitEnum, Map<String, Integer>> values, MeasurementUnitEnum measurementUnitEnum) {
        Map<String, String> valueMeasurementUnitWeight = new HashMap<>();
        int minValue = values.get(measurementUnitEnum).get(MIN_VALUE);
        int maxValue = values.get(measurementUnitEnum).get(MAX_VALUE);
        String value = String.valueOf(ThreadLocalRandom.current().nextInt(minValue, maxValue));
        String measurementUnit = measurementUnitEnum.getMeasurementUnitName();
        String weight;
        valueMeasurementUnitWeight.put(VALUE, value);
        valueMeasurementUnitWeight.put(MEASUREMENT_UNIT, measurementUnit);
        if (exercise.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)) {
            weight = getWeightForWeightLifting(value, exercise);
        } else {
            weight = "";
        }
        valueMeasurementUnitWeight.put(WEIGHT, weight);
        return valueMeasurementUnitWeight;
    }

    public static Object[] getBaseTrainingType() {
        return Arrays.stream(TrainingTypeEnum.values()).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).filter(e -> !e.isLong()).toArray();
    }

    public static Object[] getWeightLiftingExercieseForBaseTraining() {
        return Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray();
    }

    public static Object[] getGymnasticExercisesForBaseTraining() {
        return Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray();
    }

    public static Object[] getCardioExercisesForBaseTraining() {
        return Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).toArray();
    }

    public static Object[] getLongWeightLiftingExercisesForBaseTraining() {
        return Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.WEIGHTLIFTING)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).filter(ExerciseEnum::isLong).toArray();
    }

    public static Object[] getLongGymnasticExercisesForBaseTraining() {
        return Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.GYMNASTIC)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).filter(ExerciseEnum::isLong).toArray();
    }

    public static Object[] getLongCardioExercisesForBaseTraining() {
        return Arrays.stream(ExerciseEnum.values()).filter(e -> e.getExerciseType().equals(ExerciseTypeEnum.CARDIO)).filter(e -> e.getTrainingPart().equals(TrainingPartEnum.BASE_TRAINING) || e.getTrainingPart().equals(TrainingPartEnum.UNIVERSAL)).filter(ExerciseEnum::isLong).toArray();
    }

}
