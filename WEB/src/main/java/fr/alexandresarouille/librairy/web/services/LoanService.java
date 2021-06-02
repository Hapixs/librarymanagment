package fr.alexandresarouille.librairy.web.services;

import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityNotExistException;

import java.util.Collection;

public interface LoanService {
    Collection<Loan> findAllByUser(User user) throws EntityNotExistException;
}
