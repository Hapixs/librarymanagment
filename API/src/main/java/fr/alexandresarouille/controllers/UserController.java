package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/users/")
public class UserController {

    /**
     * Instance of the user service called by the rest methods
     *
     * see also:
     * {@link UserService}
     */
    @Autowired
    private UserService userService;

    // TODO: modify with the user's email
    @GetMapping("{id}")
    public User getFromId(@NotNull @PathVariable int id) throws EntityNotExistException {
        return userService.findByIdIfExist(id);
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
    @PostMapping
    public User createUser(@Valid UserDTO userDTO) throws EntityExistException {
        return userService.create(userDTO);
    }
}
