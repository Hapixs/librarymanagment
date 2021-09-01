package fr.alexandresarouille.exceptions;

/**
 * Generaly called when a loan has already been extended and can't be extend anymore
 */
public class LoanAlreadyExtendedException extends CustomRestException {

    public LoanAlreadyExtendedException() {
        super("Ce prêt à déjà été ralonger. Vous ne pouvez pas le ralonger plus.", 464);
    }

    public static final int errorCode = 464;

}
