package enums;

import lombok.Getter;

@Getter
public enum MeasurementUnitEnum {
    CAL("калорий"),
    REP("повторений"),
    SEC("секунд"),
    METTERS("метров");

    private String measurementUnitName;

     MeasurementUnitEnum(String measurementUnitName) {
        this.measurementUnitName = measurementUnitName;
    }

}
