package fr.alexandresarouille.library.api.exceptions;

/**
 * Exception called if an entity already exist in the data base
 */
public class EntityExistException extends CustomRestException {
    public EntityExistException(String message) {
        super(message, 462);
    }
}