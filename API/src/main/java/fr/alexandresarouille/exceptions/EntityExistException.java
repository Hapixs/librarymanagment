package fr.alexandresarouille.exceptions;


import lombok.NoArgsConstructor;

/**
 * Exception called if an entity already exist in the data base
 */
@NoArgsConstructor
public class EntityExistException extends Exception {
    public EntityExistException(String message) {
        super(message);
    }
}