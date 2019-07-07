package exceptions;

public class UnknowWodException extends Exception {

    public UnknowWodException() {
        super();
    }

    public UnknowWodException(String errorMessage) {
        super(errorMessage);
    }
}
