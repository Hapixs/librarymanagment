package fr.alexandresarouille.library.api.exceptions;

public abstract class CustomRestException extends Exception {

    public CustomRestException(String message, int statueCodeValue) {
        super(message);
        this.statueCodeValue=statueCodeValue;
    }

    private final int statueCodeValue;

    public int getStatueCodeValue() {
        return statueCodeValue;
    }
}
