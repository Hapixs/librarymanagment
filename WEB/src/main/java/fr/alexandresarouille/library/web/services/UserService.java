package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(UserDTO userDTO) throws EntityExistException;
}
