package fr.alexandresarouille.library.web.controllers;

import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.web.entities.UserCredential;
import fr.alexandresarouille.library.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Controller
@RequestMapping("/users")
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
        return "registeration";
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
                                   HttpSession httpSession,
                                   @ModelAttribute("user") @Valid UserDTO user) {

        try {
            userService.createUser(user);
            redirectAttributes.addAttribute("success", "Votre compte à bien été enregistrer. Vous pouvez vous connecter maintenant en cliquant ici");
            httpSession = userService.updateHttpSession(httpSession, new UserCredential(user.getEmail(), user.getPassword()));
            return "redirect:/";
        } catch (Throwable e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            redirectAttributes.addAttribute("user", user);
        }

        return "redirect:/users/register";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(Model model,
                               HttpSession httpSession,
                               RedirectAttributes redirectAttributes,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "warn", required = false) String warn,
                               @RequestParam(value = "success", required = false) String success) {

        if(Objects.nonNull(httpSession.getAttribute("username"))
                && Objects.nonNull(httpSession.getAttribute("password"))) {
            redirectAttributes.addAttribute("error", "Vous êtes déjà connecter!");
            return "redirect:/";
        }
        model.addAttribute("error", error);
        model.addAttribute("warn", warn);
        model.addAttribute("success", success);

        model.addAttribute("credential", new UserCredential());

        return "/users/login";
    }


    @PostMapping("/login")
    public String loginUser(@ModelAttribute("credential") UserCredential userCredential,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes) {

        try {
            httpSession = userService.updateHttpSession(httpSession, userCredential);
        } catch (Throwable e) {
            redirectAttributes.addAttribute("error", e.getMessage());
            return "redirect:/users/login";
        }
        return "redirect:/";
    }
}
