package fr.alexandresarouille.librairy.web.services;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(UserDTO userDTO) throws EntityExistException;
}
