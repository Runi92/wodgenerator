package generators;

import entities.TrainingSchemaEntity;
import enums.BaseTrainingSchemaEnum;
import exceptions.*;
import initiators.BaseTrainingInitiator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTrainingGenerator extends Generator {
    //TODO Равномерно распределить базовые тренировки по периоду
    public BaseTrainingGenerator(int trainingDayNumber, TrainingSchemaEntity trainingSchema) throws UnsupportedBaseTrainingException, UnsupportedExerciseCountException, UnknowWodException, UnsupportedSchemaException, UnsupportedMeasurementUnitException {
        super(trainingDayNumber, trainingSchema);
    }

    @Override
    public void init() throws UnsupportedBaseTrainingException, UnsupportedMeasurementUnitException {
        baseTrainingSchemaEnums = initBaseTrainingSchema(trainingDayNumber, trainingSchema);
        if (!baseTrainingSchemaEnums.contains(BaseTrainingSchemaEnum.R)) {
            countOfExercises = baseTrainingSchemaEnums.size();
            trainingType = BaseTrainingInitiator.initTrainingType(countOfExercises, baseTrainingSchemaEnums);
            exercisesList = BaseTrainingInitiator.initExercises(baseTrainingSchemaEnums);
            metcon = BaseTrainingInitiator.initMetcon(exercisesList, trainingType);
        }

    }
}
