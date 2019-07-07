package generators;

import entities.TrainingSchemaEntity;
import enums.BaseTrainingSchemaEnum;
import exceptions.*;
import initiators.WarmUpInitiator;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WarmUpGenerator extends Generator {

    public WarmUpGenerator(int trainingDayNumber, TrainingSchemaEntity trainingSchema) throws UnsupportedBaseTrainingException, UnsupportedExerciseCountException, UnknowWodException, UnsupportedSchemaException, UnsupportedMeasurementUnitException {
        super(trainingDayNumber, trainingSchema);
    }

    @Override
    public void init() throws UnsupportedBaseTrainingException, UnsupportedExerciseCountException, UnsupportedSchemaException, UnsupportedMeasurementUnitException {
        baseTrainingSchemaEnums = initBaseTrainingSchema(trainingDayNumber - 1, trainingSchema);
        if (!baseTrainingSchemaEnums.contains(BaseTrainingSchemaEnum.R)) {
            countOfExercises = WarmUpInitiator.initExerciseCount(baseTrainingSchemaEnums.size());
            trainingType = WarmUpInitiator.initTrainingType(countOfExercises);
            exercisesList = WarmUpInitiator.initExercises(baseTrainingSchemaEnums);
            metcon = WarmUpInitiator.initMetcon(exercisesList, trainingType);
        }
    }
}
