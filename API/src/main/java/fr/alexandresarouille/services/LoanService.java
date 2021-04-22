package fr.alexandresarouille.services;


import fr.alexandresarouille.dto.LoanDTO;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.BookNoQuantityException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.exceptions.LoanAlreadyExtendedException;
import fr.alexandresarouille.exceptions.SameBookLoanForUserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;
import java.util.Optional;


public interface LoanService {

    /**
     * Get or not a loan from the data base by his id
     *
     * @param id Id of the loan
     * @return an optional containing the loan if he was found
     */
    Optional<Loan> findById(@NotNull int id);

    /**
     * Get a loan from the data base by his id
     *
     * @param id Id of the loan
     * @return The loan by his id ({@link Loan})
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    Loan findByIdIfExist(@NotNull int id) throws EntityNotExistException;

    /**
     * Get all loans specified by a user
     *
     * @param pageRequest List Size and item to return
     * @param user Targeted user {@link User}
     * @return the list of a user's loans
     */
    Page<Loan> findAllByUser(@NotNull PageRequest pageRequest, @NotNull User user);

    /**
     * Create an new unique loan object in the data base
     *
     * @param loanDTO {@link LoanDTO}
     * @return The created loan ({@link Loan})
     * @throws EntityNotExistException {@link EntityNotExistException}
     * @throws BookNoQuantityException {@link BookNoQuantityException}
     * @throws SameBookLoanForUserException {@link SameBookLoanForUserException}
     */
    Loan create(@NotNull LoanDTO loanDTO) throws EntityNotExistException, BookNoQuantityException, SameBookLoanForUserException;

    /**
     * Extend a specified loan if the he wasn't already extended
     *
     * @param id the loan's id
     * @return {@link Loan}
     * @throws EntityNotExistException {@link EntityNotExistException}
     * @throws LoanAlreadyExtendedException {@link LoanAlreadyExtendedException}
     */
    Loan extendLoan(@NotNull int id) throws EntityNotExistException, LoanAlreadyExtendedException;
}
