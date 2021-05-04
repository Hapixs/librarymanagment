package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/users")
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
     * Find a user by is id
     *
     * @param id the user's id
     * @return see {@link User}
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getFromId(@NotNull @PathVariable int id) throws EntityNotExistException {
        return new ResponseEntity<>(userService.findByIdIfExist(id), HttpStatus.OK);
    }

    /**
     * Create a user instance in the database if the specified email dose not already exist
     *
     * see also:
     * {@link UserService#create(UserDTO)}
     *
     * @return {@link User}
     * @throws EntityExistException {@link EntityExistException}
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws EntityExistException {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }
}
