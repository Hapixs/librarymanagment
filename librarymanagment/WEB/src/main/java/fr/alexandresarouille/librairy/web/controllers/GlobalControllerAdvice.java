package fr.alexandresarouille.librairy.web.controllers;

import fr.alexandresarouille.entities.Role;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice("fr.alexandresarouille.librairy.web.controllers")
public class GlobalControllerAdvice {


    @ModelAttribute("error_message")
    public String blankErrorMessage() {
        return "";
    }

    @ModelAttribute("success_message")
    public String blankSuccessMessage() {
        return "";
    }

    @ModelAttribute("roles")
    public Role[] roleList() {
        return Role.values();
    }

}
