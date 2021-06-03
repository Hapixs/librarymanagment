package fr.alexandresarouille.library.api.controllers;

import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.api.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Rest controller for the user entity
 * Called with the path: {host}/users/
 *
 * Working with the UserService {@link UserService}
 */
@RestController
@RequestMapping("/api")
@Api(value = "User's controller")
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
    @ApiOperation(value = "Create a new user", response = User.class, tags = "createUser")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 402, message = "Forbidden"),
            @ApiResponse(code = 462, message = "User already exist")
    })
    @PostMapping("/all/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO) throws EntityExistException {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get details about a specified user", response = UserDetails.class, tags = "getDetailsAboutUser")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "User found"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 402, message = "Forbidden"),
            @ApiResponse(code = 460, message = "Username doesn't exist")
    })
    @GetMapping("/all/users/{username}")
    public ResponseEntity<UserDetails> login(@Valid @PathVariable String username) throws UsernameNotFoundException {
        return new ResponseEntity<>(userService.loadUserByUsername(username), HttpStatus.FOUND);
    }
}
