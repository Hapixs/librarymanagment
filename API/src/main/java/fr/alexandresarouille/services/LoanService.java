package fr.alexandresarouille.services;


import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;

import java.util.Optional;


public interface LoanService {
    
    /**
     * Get or not a loan from the data base by his id
     * @param id -> Id of the loan
     * @return an optional containing the loan if he was found
     */
    Optional<Loan> findById(int id);

    /**
     * Get a loan from the data base by his id
     * @param id -> Id of the loan
     * @return -> The loan by his id
     * @throws EntityNotExistException -> Throwed if the id dosn't match with any loan
     */
    Loan findByIdIfExist(int id) throws EntityNotExistException;

    /**
     * Create an new unique loan object in the data base
     * @param loan -> The loan to create (without id)
     */
    void create(Loan loan);

    /**
     * Delete a unique loan object in the data base by is id
     * @param id -> The id of the targeted loan to be deleted
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any loan
     */
    void delete(int id) throws EntityNotExistException;

    /**
     * Edit à loan data by is id
     * @param id -> Id of the targeted loan
     * @param loan -> changes to make
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any loan
     */
    void edit(int id, Loan loan) throws EntityNotExistException;

}
