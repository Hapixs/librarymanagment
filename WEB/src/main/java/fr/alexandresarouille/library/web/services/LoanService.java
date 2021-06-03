package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import org.springframework.security.core.Authentication;

import java.util.Collection;


public interface LoanService {
    Collection<Loan> findAllByUser(User user, Authentication authentication) throws EntityNotExistException;
}
