package fr.alexandresarouille.library.api.exceptions;

/**
 * Generaly called when a book as no more quantity to get loaned
 */
public class BookNoQuantityException extends CustomRestException {
    public BookNoQuantityException() {
        super("Ce livre n'est pas disponible. Réessayer plus tard.", 461);
    }

    public static final int errorCode = 461;
}
