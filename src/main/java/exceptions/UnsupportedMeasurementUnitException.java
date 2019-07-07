package exceptions;

public class UnsupportedMeasurementUnitException extends Exception {

    public UnsupportedMeasurementUnitException() {
        super();
    }

    public UnsupportedMeasurementUnitException(String errorMessage) {
        super(errorMessage);
    }

}
