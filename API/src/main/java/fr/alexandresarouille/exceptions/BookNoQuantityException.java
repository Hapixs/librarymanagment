package fr.alexandresarouille.exceptions;

/**
 * Generaly called when a book as no more quantity to get loaned
 */
public class BookNoQuantityException extends Exception {

    public BookNoQuantityException(){
        super("Ce livre n'est pas disponible. Réessayer plus tard.");
    }

    public BookNoQuantityException(String message) {
        super(message);
    }
}
