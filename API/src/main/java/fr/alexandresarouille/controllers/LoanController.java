package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.LoanDTO;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.exceptions.BookNoQuantityException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.exceptions.LoanAlreadyExtendedException;
import fr.alexandresarouille.exceptions.SameBookLoanForUserException;
import fr.alexandresarouille.services.LoanService;
import fr.alexandresarouille.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class LoanController {

    /**
     * Instance of the loan services called by the rest methods
     * {@link LoanService}
     */
    @Autowired
    private LoanService loanService;

    @Autowired
    private UserService userService;

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
    @PostMapping("/users/loans/")
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody LoanDTO loanDTO) throws EntityNotExistException, SameBookLoanForUserException, BookNoQuantityException {
        return new ResponseEntity<>(loanService.create(loanDTO), HttpStatus.OK);
    }

    /**
     * Extend by 4 week a specified loan if it not already been extended
     *
     * @param id the loan's id
     * @return {@link Loan}
     * @throws EntityNotExistException {@link EntityNotExistException}
     * @throws LoanAlreadyExtendedException {@link LoanAlreadyExtendedException}
     */
    @PutMapping("/users/loans/extend/{id}")
    public ResponseEntity<Loan> extendLoan(@NotNull @PathVariable int id) throws EntityNotExistException, LoanAlreadyExtendedException {
        return new ResponseEntity<>(loanService.extendLoan(id), HttpStatus.OK);
    }

    /**
     * List all exceeded loans
     *
     * @return a collection of loans
     */
    @GetMapping("/batch/loans/exceeded")
    public ResponseEntity<Collection<Loan>> getExceededLoans() {
        return new ResponseEntity<>(loanService.findAllExceededLoan(), HttpStatus.OK);
    }

    /**
     * List all loan from a user
     *
     * @param id the user's id
     * @return a collection of loans
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    @GetMapping("/users/loans/fromUser/{id}")
    public ResponseEntity<Collection<Loan>> findAllFromUser(@NotNull @PathVariable int id) throws EntityNotExistException {
        return new ResponseEntity<>(loanService.findAllByUser(userService.findByIdIfExist(id)), HttpStatus.OK);
    }
}
