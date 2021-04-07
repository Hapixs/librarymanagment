package fr.alexandresarouille.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotExistException extends Exception {
    public EntityNotExistException(String message) {
        super(message);
    }
}
