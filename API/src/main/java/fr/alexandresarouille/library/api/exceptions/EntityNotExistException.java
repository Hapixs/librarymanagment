package fr.alexandresarouille.library.api.exceptions;


public class EntityNotExistException extends CustomRestException {
    public EntityNotExistException(String message) {
        super(message, 463);
    }
}
