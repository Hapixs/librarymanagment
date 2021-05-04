package fr.alexandresarouille.services;


import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * This service work for the user's feature
 * He generally work with {@link fr.alexandresarouille.dao.UserRepository}
 */
public interface UserService /* extends UserDetailsService*/ {

    /**
     * Get or not a user from the data base by his id
     *
     * @param id Id of the user
     * @return an optional containing the user if he was found
     */
    Optional<User> findById(@NotNull int id);

    /**
     * Get a user from the data base by his id
     *
     * @param id Id of the user
     * @return The user by his id ({@link User})
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    User findByIdIfExist(@NotNull int id) throws EntityNotExistException;

    /**
     * Get a user from the data base by his email
     *
     * @param email Email of the user
     * @return The user by his email ({@link EntityNotExistException})
     */
    Optional<User> findByEmail(@NotNull String email);

    /**
     * Create an new unique user object in the data base
     *
     * @param userDTO {@link UserDTO}
     * @return The created user ({@link User})
     * @throws EntityExistException {@link EntityExistException}
     */
    User create(@NotNull UserDTO userDTO) throws EntityExistException;
}
