package exceptions;

public class UnsupportedBaseTrainingSchemaException extends Exception {

    public UnsupportedBaseTrainingSchemaException() {
        super();
    }

    public UnsupportedBaseTrainingSchemaException(String errorMessage) {
        super(errorMessage);
    }
}
