package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.LoanDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.BookNoQuantityException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.exceptions.LoanAlreadyExtendedException;
import fr.alexandresarouille.exceptions.SameBookLoanForUserException;
import fr.alexandresarouille.services.BookService;
import fr.alexandresarouille.services.LoanService;
import fr.alexandresarouille.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Rest controller for the loan entity
 * Called with the path: {host}/loans/
 *
 * Working with the loanService {@link LoanService}
 */
@RestController
@RequestMapping("/loans/")
public class LoanController {

    /**
     * Instance of the loan services called by the rest methods
     * {@link LoanService}
     */
    @Autowired
    private LoanService loanService;

    /**
     * Create a loan instance in the database
     *
     * see also:
     * {@link LoanService#create(LoanDTO)}
     *
     * @param loanDTO {@link LoanDTO}
     * @return {@link Loan}
     * @throws EntityNotExistException {@link EntityNotExistException}
     * @throws SameBookLoanForUserException {@link SameBookLoanForUserException}
     * @throws BookNoQuantityException {@link BookNoQuantityException}
     */
    @PostMapping
    public Loan createLoan(@Valid LoanDTO loanDTO) throws EntityNotExistException, SameBookLoanForUserException, BookNoQuantityException {
        return loanService.create(loanDTO);
    }

    /**
     * Extend by 4 week a specified loan if it not already been extended
     *
     * @param id the loan's id
     * @return {@link Loan}
     * @throws EntityNotExistException {@link EntityNotExistException}
     * @throws LoanAlreadyExtendedException {@link LoanAlreadyExtendedException}
     */
    @PutMapping("{id}")
    public Loan extendLoan(@NotNull @PathVariable int id) throws EntityNotExistException, LoanAlreadyExtendedException {
        return loanService.extendLoan(id);
    }

    @GetMapping("/exceeded")
    public Collection<Loan> getExceededLoans() {
        return loanService.getAllExceededLoan();
    }
}
