package fr.alexandresarouille.services;


import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;

import java.util.Optional;

public interface UserService {

    /**
     * Get or not a user from the data base by his id
     * @param id -> Id of the user
     * @return an optional containing the user if he was found
     */
    Optional<User> findById(int id);

    /**
     * Get a user from the data base by his id
     * @param id -> Id of the user
     * @return -> The user by his id
     * @throws EntityNotExistException -> Throwed if the id dosn't match with any user
     */
    User findByIdIfExist(int id) throws EntityNotExistException;

    /**
     * Create an new unique user object in the data base
     * @param user -> The user to create (without id)
     * @throws EntityExistException -> Throwed if a user with the same name already exist
     */
    void create(User user) throws EntityExistException;

    /**
     * Delete a unique user object in the data base by is id
     * @param id -> The id of the targeted user to be deleted
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any user
     */
    void delete(int id) throws EntityNotExistException;

    /**
     * Edit à user data by is id
     * @param id -> Id of the targeted user
     * @param user -> changes to make
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any user
     */
    void edit(int id, User user) throws EntityNotExistException;
}
