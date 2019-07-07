package initiators;

import entities.MetconEntity;
import enums.*;
import exceptions.UnknowWodException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WodInitiator {

    public static MetconEntity initMetcon() throws UnknowWodException {
        int numberOfWod = ThreadLocalRandom.current().nextInt(0, WodEnum.values().length);
        WodEnum wod = WodEnum.values()[numberOfWod];
        MetconEntity metcon = initWod(wod);
        return metcon;
    }

    private static MetconEntity initWod(WodEnum wod) throws UnknowWodException {
        //TODO Исправить все комплексы, в которых в меткон идут одиннаковые упражнения
        switch (wod) {
            case EASY_MARY:
                return initEasyMary(wod);
            case NASTY_GIRLS:
                return initNastyGirls(wod);
            case GWEN:
                return initGwen(wod);
            case MARY:
                return initMary(wod);
            case LYNNE:
                return initLynne(wod);
            case AMANDA:
                return initAmanda(wod);
            case FRELEN:
                return initFrelen(wod);
            case KARABEL:
                return initKarabel(wod);
            case MARGURTA:
                return initMargurta(wod);
            case MAGGIE:
                return initMaggie(wod);
            case NICOLE:
                return initNicole(wod);
            /*case EVA:
                return initEva(wod);
            case FRAN:
                return initFran(wod);
            case ANGIE:
                return initAngie(wod);
            case ANNIE:
                return initAnnie(wod);
            case CANDY:
                return initCandy(wod);
            case CINDY:
                return initCindy(wod);
            case DIANE:
                return initDiane(wod);
            case GRACE:
                return initGrace(wod);
            case HELEN:
                return initHelen(wod);
            case KAREN:
                return initKaren(wod);
            case KELLY:
                return initKelly(wod);
            case LINDA:
                return initLinda(wod);
            case NANCY:
                return initNancy(wod);
            case ISABEL:
                return initIsabel(wod);
            case JACKIE:
                return initJackie(wod);
            case BARBARA:
                return initBarbara(wod);
            case CHELSEA:
                return initChelsea(wod);
            case CHRISTINE:
                return initChristine(wod);
            case ELIZABETH:
                return initElizabeth(wod);*/
            default:
                throw new UnknowWodException("Неизвестный WOD!");
        }
    }

    private static MetconEntity initEasyMary(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.AMRAP;
        String duration = "20 минут";
        String strictHandStandPushUpReps = "5";
        String strictPullUpReps = "10";
        String airSquadReps = "25";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodGymnasticExerciseEnum.STRICT_HAND_STAND_PUSH_UP.getExerciseName(), String.join(" ", strictHandStandPushUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.STRICT_PULL_UP.getExerciseName(), String.join(" ", strictPullUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.AIR_SQUAD.getExerciseName(), String.join(" ", airSquadReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initMaggie(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "5 раундов";
        String strictHandStandPushUpReps = "20";
        String strictPullUpReps = "40";
        String pistolSquatReps = "60";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodGymnasticExerciseEnum.STRICT_HAND_STAND_PUSH_UP.getExerciseName(), String.join(" ", strictHandStandPushUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.STRICT_PULL_UP.getExerciseName(), String.join(" ", strictPullUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.PISTOL_SQUAT.getExerciseName(), String.join(" ", pistolSquatReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initKarabel(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "10 раундов";
        String powerSnatchReps = "3";
        String powerSnatchWeight = "60 кг";
        String wallBallReps = "15";
        String wallBallWeight = "9 кг";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodWeightLiftingExerciseEnum.POWER_SNATCH.getExerciseName(), String.join(" ", powerSnatchReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), powerSnatchWeight));
        metconMap.put(WodWeightLiftingExerciseEnum.WALL_BALL.getExerciseName(), String.join(" ", wallBallReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), wallBallWeight));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initFrelen(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "5 раундов";
        String runMetres = "800";
        String dumbbellThrusterReps = "15";
        String dumbbellThrustersWeight = "20 кг";
        String pullUpReps = "15";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodCardioExerciseEnum.RUN.getExerciseName(), String.join(" ", runMetres, MeasurementUnitEnum.METTERS.getMeasurementUnitName()));
        metconMap.put(WodWeightLiftingExerciseEnum.DUMBBELL_THRUSTERS.getExerciseName(), String.join(" ", dumbbellThrusterReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), dumbbellThrustersWeight));
        metconMap.put(WodGymnasticExerciseEnum.STRICT_PULL_UP.getExerciseName(), String.join(" ", pullUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initGwen(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "1 раунд";
        String cleanAndJerkFirstReps = "15";
        String cleanAndJerkSecondReps = "12";
        String cleanAndJerkThirdReps = "9";
        String touchAndGo = "touchAndGo";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodWeightLiftingExerciseEnum.CLEAN_AND_JERK.getExerciseName() + "1", String.join(" ", cleanAndJerkFirstReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), touchAndGo));
        metconMap.put(WodWeightLiftingExerciseEnum.CLEAN_AND_JERK.getExerciseName() + "2", String.join(" ", cleanAndJerkSecondReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), touchAndGo));
        metconMap.put(WodWeightLiftingExerciseEnum.CLEAN_AND_JERK.getExerciseName() + "3", String.join(" ", cleanAndJerkThirdReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), touchAndGo));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initMargurta(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "50 раундов";
        String burpeeReps = "1";
        String pushUpReps = "1";
        String jumpingJackReps = "1";
        String sitUpReps = "1";
        String handStandReps = "1";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodCardioExerciseEnum.BURPEE.getExerciseName(), String.join(" ", burpeeReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.PUSH_UP.getExerciseName(), String.join(" ", pushUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodCardioExerciseEnum.JUMPING_JACK.getExerciseName(), String.join(" ", jumpingJackReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.SIT_UP.getExerciseName(), String.join(" ", sitUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.HAND_STAND.getExerciseName(), String.join(" ", handStandReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initNicole(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.AMRAP;
        String duration = "20 минут";
        String runMeters = "400";
        String pullUpReps = "max";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodCardioExerciseEnum.RUN.getExerciseName(), String.join(" ", runMeters, MeasurementUnitEnum.METTERS.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.STRICT_PULL_UP.getExerciseName(), String.join(" ", pullUpReps, MeasurementUnitEnum.METTERS.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initLynne(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "5 раундов";
        String benchPressRep = "max";
        String benchPressWeight = "вес тела";
        String pullUpReps = "max";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodWeightLiftingExerciseEnum.BENCH_PRESS.getExerciseName(), String.join(" ", benchPressRep, MeasurementUnitEnum.REP.getMeasurementUnitName(), benchPressWeight));
        metconMap.put(WodGymnasticExerciseEnum.STRICT_PULL_UP.getExerciseName(), String.join(" ", pullUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initMary(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.AMRAP;
        String duration = "20 минут";
        String handStandPushUpReps = "5";
        String pistolSquatReps = "10";
        String pullUpReps = "15";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodGymnasticExerciseEnum.STRICT_HAND_STAND_PUSH_UP.getExerciseName(), String.join(" ", handStandPushUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.PISTOL_SQUAT.getExerciseName(), String.join(" ", pistolSquatReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.STRICT_PULL_UP.getExerciseName(), String.join(" ", pullUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initAmanda(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "1 раунд";
        String ringMuscleUpFirstReps = "9";
        String ringMuscleUpSeconReps = "8";
        String ringMuscleUpThirdReps = "5";
        String snatchFirstReps = "9";
        String snatchSeconReps = "8";
        String snatchThirdReps = "5";
        String snatchWeight = "60 кг";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodGymnasticExerciseEnum.RING_MUSCLE_UP.getExerciseName(), String.join(" ", ringMuscleUpFirstReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodWeightLiftingExerciseEnum.SNATCH.getExerciseName(), String.join(" ", snatchFirstReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), snatchWeight));
        metconMap.put(WodGymnasticExerciseEnum.RING_MUSCLE_UP.getExerciseName(), String.join(" ", ringMuscleUpSeconReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodWeightLiftingExerciseEnum.SNATCH.getExerciseName(), String.join(" ", snatchSeconReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), snatchWeight));
        metconMap.put(WodGymnasticExerciseEnum.RING_MUSCLE_UP.getExerciseName(), String.join(" ", ringMuscleUpThirdReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodWeightLiftingExerciseEnum.SNATCH.getExerciseName(), String.join(" ", snatchThirdReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), snatchWeight));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    private static MetconEntity initNastyGirls(WodEnum wod) {
        String name = wod.getWodName();
        TrainingTypeEnum trainingType = TrainingTypeEnum.RFT;
        String duration = "3 раунда";
        String airSquatReps = "50";
        String ringMuscleUpReps = "7";
        String hangCleanReps = "10";
        String hangCleanWeight = "60 кг";
        Map<String, String> metconMap = new HashMap<>();
        metconMap.put(WodGymnasticExerciseEnum.AIR_SQUAD.getExerciseName(), String.join(" ", airSquatReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodGymnasticExerciseEnum.RING_MUSCLE_UP.getExerciseName(), String.join(" ", ringMuscleUpReps, MeasurementUnitEnum.REP.getMeasurementUnitName()));
        metconMap.put(WodWeightLiftingExerciseEnum.HANG_CLEAN.getExerciseName(), String.join(" ", hangCleanReps, MeasurementUnitEnum.REP.getMeasurementUnitName(), hangCleanWeight));
        return MetconEntity.builder().name(name).metconTrainingType(trainingType).duration(duration).metconMap(metconMap).build();
    }

    //TODO Дописать методы инициализаций комплексов WOD

}
