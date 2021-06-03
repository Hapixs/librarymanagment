package fr.alexandresarouille.library.batch.services;

import fr.alexandresarouille.library.api.entities.Loan;

import java.util.Collection;

public interface LoanService {

    Collection<Loan> findAllExceededLoan();

}
