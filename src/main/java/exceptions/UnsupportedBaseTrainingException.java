package exceptions;

public class UnsupportedBaseTrainingException extends Exception {
    public UnsupportedBaseTrainingException() {
        super();
    }

    public UnsupportedBaseTrainingException(String errorMessage) {
        super(errorMessage);
    }
}
