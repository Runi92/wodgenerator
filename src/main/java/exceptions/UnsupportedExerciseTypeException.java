package exceptions;

public class UnsupportedExerciseTypeException extends Exception {

    public UnsupportedExerciseTypeException(String errorMessage) {
        super(errorMessage);
    }

    public UnsupportedExerciseTypeException() {
        super();
    }
}
