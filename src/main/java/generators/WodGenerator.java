package generators;

import exceptions.*;
import initiators.WodInitiator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WodGenerator extends Generator {

    public WodGenerator() throws UnsupportedBaseTrainingException, UnknowWodException, UnsupportedExerciseCountException, UnsupportedSchemaException, UnsupportedMeasurementUnitException {
        super();
    }

    public
    @Override
    void init() throws UnknowWodException {
        metcon = WodInitiator.initMetcon();
    }
}
