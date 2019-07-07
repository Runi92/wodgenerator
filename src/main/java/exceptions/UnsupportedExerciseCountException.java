package exceptions;

public class UnsupportedExerciseCountException extends Exception {

    public UnsupportedExerciseCountException() {
        super();
    }

    public UnsupportedExerciseCountException(String errorMessage) {
        super(errorMessage);
    }
}
