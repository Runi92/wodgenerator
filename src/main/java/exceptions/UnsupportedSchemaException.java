package exceptions;

public class UnsupportedSchemaException extends Exception {

    public UnsupportedSchemaException() {
        super();
    }

    public UnsupportedSchemaException(String errorMessage) {
        super(errorMessage);
    }
}
