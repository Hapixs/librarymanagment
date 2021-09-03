package fr.alexandresarouille.exceptions;


public class EntityNotExistException extends CustomRestException {
    public EntityNotExistException(String message) {
        super(message, 463);
    }

    public static final int errorCode = 463;
}
