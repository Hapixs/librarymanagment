package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.LoanDTO;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import javax.servlet.http.HttpSession;
import java.util.Collection;


public interface LoanService {
    Collection<Loan> findAllByUser(User user, HttpSession session) throws EntityNotExistException;

    Loan createLoan(LoanDTO loanDTO, HttpSession session) throws EntityNotExistException, UnknownHttpStatusCodeException;

    void extendLoan(Integer loanId);
}
