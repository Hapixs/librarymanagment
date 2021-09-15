package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.web.entities.UserCredential;

import javax.servlet.http.HttpSession;

public interface UserService /* extends UserDetailsService */ {
    void createUser(UserDTO userDTO) throws EntityExistException;
    HttpSession updateHttpSession(HttpSession httpSession, UserCredential userCredential) throws Throwable;

    User findByUsername(String username, String password);
}
