package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@NotNull UserDTO userDTO) throws EntityExistException {
        return userService.create(convertToUser(userDTO));
    }

    @PutMapping("{id}")
    public User editUser(@NotNull @PathVariable int id, UserDTO userDTO) throws EntityNotExistException {
        return userService.edit(id, convertToUser(userDTO));
    }

    @DeleteMapping("{id}")
    public void deleteUser(@NotNull @PathVariable int id) throws EntityNotExistException {
        userService.delete(id);
    }

    private User convertToUser(@NotNull UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }
}
