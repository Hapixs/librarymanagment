package fr.alexandresarouille.services;

import fr.alexandresarouille.entities.Loan;

import java.util.Collection;

public interface LoanService {

    Collection<Loan> findAllExceededLoan();

}
