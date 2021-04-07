package fr.alexandresarouille.services;


import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.NotNull;
import java.util.Optional;


public interface LoanService {
    
    /**
     * Get or not a loan from the data base by his id
     * @param id -> Id of the loan
     * @return an optional containing the loan if he was found
     */
    Optional<Loan> findById(@NotNull int id);

    /**
     * Get a loan from the data base by his id
     * @param id -> Id of the loan
     * @return -> The loan by his id
     * @throws EntityNotExistException -> Throwed if the id dosn't match with any loan
     */
    Loan findByIdIfExist(@NotNull int id) throws EntityNotExistException;

    /**
     * Create an new unique loan object in the data base
     * @param loan -> The loan to create (without id)
     * @return
     */
    Loan create(@NotNull Loan loan);

    /**
     * Delete a unique loan object in the data base by is id
     * @param id -> The id of the targeted loan to be deleted
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any loan
     */
    void delete(@NotNull int id) throws EntityNotExistException;

    /**
     * Edit à loan data by is id
     * @param id -> Id of the targeted loan
     * @param loan -> changes to make
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any loan
     * @return
     */
    Loan edit(@NotNull int id, @NotNull Loan loan) throws EntityNotExistException;

    /**
     * Get all loans specified by a user
     * @param pageRequest -> List Size and item to return
     * @param user -> Targeted user
     * @return the list of a user's loans
     */
    Page<Loan> findAllByUser(@NotNull PageRequest pageRequest, @NotNull User user);
}
