package fr.alexandresarouille.exceptions;


/**
 * Generaly called when a user already have a loan for a specified book so he can't do another loan for the same book
 */
public class SameBookLoanForUserException extends Exception {

    public SameBookLoanForUserException() {
        super("Vous avez déjà un prêt en cours pour ce livre.");
    }

    public SameBookLoanForUserException(String message) {
        super(message);
    }
}
