package enums;

import lombok.Getter;

@Getter
public enum WodITrainingTypeEnum {

    EMOM("EMOM"),
    EMO2M("EMO2M"),
    AFAP("AFAP"),
    AMRAP("AMRAP"),
    RFT("RFT"),
    TABATA("TABATA");

    private String trainingTypeName;

    WodITrainingTypeEnum(String trainingTypeName) {
        this.trainingTypeName = trainingTypeName;
    }
}
