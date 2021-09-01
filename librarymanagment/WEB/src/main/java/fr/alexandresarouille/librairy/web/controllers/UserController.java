package fr.alexandresarouille.librairy.web.controllers;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.librairy.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     * @param model The model
     * @return the register page
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("error", redirectAttributes.getAttribute("error"));
        model.addAttribute("success", redirectAttributes.getAttribute("success"));
        model.addAttribute("user", new UserDTO());
        return "";
    }

    /**
     * Create the user in the database
     *
     * @param model the model
     * @param user  the user's information
     * @return The Login page / register page
     */
    @PostMapping("/register")
    public String postRegisterUser(Model model,
                                   RedirectAttributes redirectAttributes,
                                   @RequestAttribute("user") @Valid UserDTO user) {

        try {
            userService.createUser(user);
            redirectAttributes.addAttribute("success", "Votre compte à bien été enregistrer. Vous pouvez vous connecter maintenant en cliquant ici");
        } catch (EntityExistException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
        }


        return "redirect:/users/register";
    }
}
