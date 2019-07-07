package generators;

import entities.MetconEntity;
import entities.TrainingSchemaEntity;
import enums.*;
import exceptions.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Generator {
    protected int trainingDayNumber;
    protected int countOfExercises;
    protected TrainingTypeEnum trainingType;
    protected List<BaseTrainingSchemaEnum> baseTrainingSchemaEnums;
    protected List<ExerciseEnum> exercisesList;
    protected TrainingSchemaEntity trainingSchema;
    protected MetconEntity metcon;

    public Generator() throws UnsupportedBaseTrainingException, UnknowWodException, UnsupportedExerciseCountException, UnsupportedSchemaException, UnsupportedMeasurementUnitException {
        init();
    }

    public Generator(int trainingDayNumber, TrainingSchemaEntity trainingSchema) throws UnsupportedBaseTrainingException, UnsupportedExerciseCountException, UnknowWodException, UnsupportedSchemaException, UnsupportedMeasurementUnitException {
        this.trainingDayNumber = trainingDayNumber;
        this.trainingSchema = trainingSchema;
        init();
    }

    abstract void init() throws UnsupportedBaseTrainingException, UnsupportedExerciseCountException, UnknowWodException, UnsupportedSchemaException, UnsupportedMeasurementUnitException;

    /**
     * Метод инициализации списка схем основной тренировки
     * @param trainingDayNumber номер тренировочного дня
     * @param trainingSchema сущнность схемы тренировки
     * @return список базовых схем тренировок
     * @throws UnsupportedBaseTrainingException
     */
    public List<BaseTrainingSchemaEnum> initBaseTrainingSchema(int trainingDayNumber, TrainingSchemaEntity trainingSchema) throws UnsupportedBaseTrainingException {
        String baseTrainingSchemaName = trainingSchema.getBaseSchemas().get(trainingDayNumber - 1);
        List<BaseTrainingSchemaEnum> baseTrainingSchemaEnums = new ArrayList<>();
        for (Character baseTrainingSchemaPart : baseTrainingSchemaName.toCharArray()) {
            if (baseTrainingSchemaPart == 'C') {
                baseTrainingSchemaEnums.add(BaseTrainingSchemaEnum.C);
            } else if (baseTrainingSchemaPart == 'G') {
                baseTrainingSchemaEnums.add(BaseTrainingSchemaEnum.G);
            } else if (baseTrainingSchemaPart == 'W') {
                baseTrainingSchemaEnums.add(BaseTrainingSchemaEnum.W);
            } else if (baseTrainingSchemaPart == 'R') {
                baseTrainingSchemaEnums.add(BaseTrainingSchemaEnum.R);
            } else {
                throw new UnsupportedBaseTrainingException(String.join("", "Неподдерживаемая схема тренировки", baseTrainingSchemaPart.toString(), ".Схема должна содержать только кардио (C), гимнастическую (G), тяжелоатлетическую (W) тренировку или отдых (R)."));
            }
        }
        return baseTrainingSchemaEnums;
    }

    public String generate() {
        if (metcon == null) {
            return RestEnum.REST.getRestName();
        } else {
            return metcon.toString();
        }
    }
}
