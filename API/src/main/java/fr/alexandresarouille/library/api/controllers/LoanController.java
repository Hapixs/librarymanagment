package fr.alexandresarouille.library.api.controllers;

import fr.alexandresarouille.library.api.entities.dto.LoanDTO;
import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.exceptions.BookNoQuantityException;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.api.exceptions.LoanAlreadyExtendedException;
import fr.alexandresarouille.library.api.exceptions.SameBookLoanForUserException;
import fr.alexandresarouille.library.api.services.LoanService;
import fr.alexandresarouille.library.api.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Loans")
@RestController
@RequestMapping("/api")
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
    @ApiOperation(value = "Create a loan in the database", response = Book.class, tags = "createLoan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Loan created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = EntityNotExistException.errorCode, message = "Not found"),
            @ApiResponse(code = SameBookLoanForUserException.errorCode, message = "Same book loan for user"),
            @ApiResponse(code = BookNoQuantityException.errorCode, message = "No enough quantity of this book")
    })
    @PostMapping("/users/loans/")
    public ResponseEntity<Loan> createLoan(@Valid @RequestBody LoanDTO loanDTO) throws EntityNotExistException, SameBookLoanForUserException, BookNoQuantityException {
        return new ResponseEntity<>(loanService.create(loanDTO), HttpStatus.CREATED);
    }

    /**
     * Extend by 4 week a specified loan if it not already been extended
     *
     * @param id the loan's id
     * @return {@link Loan}
     * @throws EntityNotExistException {@link EntityNotExistException}
     * @throws LoanAlreadyExtendedException {@link LoanAlreadyExtendedException}
     */
    @ApiOperation(value = "Extend a loan duration by 4 weeks", response = Loan.class, tags = "extendLoan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Loan extended"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = EntityNotExistException.errorCode, message = "Loan not found"),
            @ApiResponse(code = LoanAlreadyExtendedException.errorCode, message = "Loan already extended")
    })
    @PutMapping("/users/loans/extend/{id}")
    public ResponseEntity<Loan> extendLoan(@NotNull @PathVariable int id) throws EntityNotExistException, LoanAlreadyExtendedException {
        return new ResponseEntity<>(loanService.extendLoan(id), HttpStatus.OK);
    }

    /**
     * List all exceeded loans
     *
     * @return a collection of loans
     */
    @ApiOperation(value = "Find all exceeded and not returned loans in the database", response = Collection.class, tags = "getAllExceededLoans")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 402, message = "Forbbiden")
    })
    @GetMapping("/batch/loans/exceeded")
    public ResponseEntity<Collection<Loan>> getExceededLoans() {
        return new ResponseEntity<>(loanService.findAllExceededLoan(), HttpStatus.FOUND);
    }

    /**
     * List all loan from a user
     *
     * @param id the user's id
     * @return a collection of loans
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    @ApiOperation(value = "Find all loan from a user", response = Collection.class, tags = "findAllLoanFromUser")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 402, message = "Forbidden"),
            @ApiResponse(code = EntityNotExistException.errorCode, message = "User doesn't exist")
    })
    @GetMapping("/users/loans/{id}")
    public ResponseEntity<Collection<Loan>> findAllFromUser(@NotNull @PathVariable int id) throws EntityNotExistException {
        return new ResponseEntity<>(loanService.findAllByUser(userService.findByIdIfExist(id)), HttpStatus.FOUND);
    }
}
