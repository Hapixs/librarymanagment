package fr.alexandresarouille.exceptions;

import lombok.AllArgsConstructor;


/**
 * Exception called if an entity already exist in the data base
 */
@AllArgsConstructor
public class EntityExistException extends Exception {
    public EntityExistException(String message) {
        super(message);
    }
}