package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.LoginDTO;
import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Rest controller for the user entity
 * Called with the path: {host}/users/
 *
 * Working with the UserService {@link UserService}
 */
@RestController
public class UserController {
    /**
     * Instance of the user service called by the rest methods
     *
     * see also:
     * {@link UserService}
     */
    @Autowired
    private UserService userService;

    /**
     * Create a user instance in the database if the specified email dose not already exist
     *
     * see also:
     * {@link UserService#create(UserDTO)}
     *
     * @return {@link User}
     * @throws EntityExistException {@link EntityExistException}
     */
    @PostMapping("/all/users/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws EntityExistException {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @GetMapping("/all/users/loadFromUsername/{username}")
    public ResponseEntity<UserDetails> login(@Valid @PathVariable String username) throws EntityNotExistException {
        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.OK);
    }
}
