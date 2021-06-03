package fr.alexandresarouille.library.api.services;


import fr.alexandresarouille.library.api.repositories.UserRepository;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * This service work for the user's feature
 * He generally work with {@link UserRepository}
 */
public interface UserService  extends UserDetailsService {

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

    User findByMailIfExist(@NotNull String email) throws UsernameNotFoundException;

    /**
     * Create an new unique user object in the data base
     *
     * @param userDTO {@link UserDTO}
     * @return The created user ({@link User})
     * @throws EntityExistException {@link EntityExistException}
     */
    User create(@NotNull UserDTO userDTO) throws EntityExistException;

}
