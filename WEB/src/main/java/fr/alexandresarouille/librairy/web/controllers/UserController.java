package fr.alexandresarouille.librairy.web.controllers;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.librairy.web.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("users")
public class UserController {

    private static StringBuilder url = Application.restHostURL.append("users/");


    @Autowired
    private RestTemplate restTemplate;


    /**
     * @param model The model
     * @return the register page
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "";
    }

    /**
     * Create the user in the database
     * @param model the model
     * @param user the user's information
     * @return The Login page / register page
     */
    @PostMapping("/register")
    public String postRegisterUser(Model model,
                                   @RequestAttribute("user") @Validated UserDTO user) {

        // POST TO REST CONTROLLER
        ResponseEntity<? extends User> response
                = restTemplate.postForEntity(url.toString(), user, User.class);

        if(response.getStatusCode().isError()) {
            //Todo: Catch error;
        }
        
        return "";
    }


    /**
     * Get edit page for a user
     * @param model The model
     * @param id the user's id
     * @return the edit page for a specified user
     */
    @GetMapping("/edit/{id}")
    public String getEditUser(Model model,
                              @PathVariable int id) {
        model.addAttribute("id", id);


                                      // Autowired possible ?
        model.addAttribute("user", new UserDTO());
        ResponseEntity<? extends User> response
                = restTemplate.getForEntity(url.append(id).toString(), User.class);

        if(response.getStatusCode().isError()) {
            // TODO: Catch error
        } else
            model.addAttribute("targetUser", response.getBody());

        return "";
    }

    /**
     * Edit the user with specified data
     * @param model the model
     * @param userDTO New informations to put in the data base for the user
     * @param id the user's id
     * @return redirect to the edit page or the last page before editing
     */
    @PutMapping("/edit/{id}")
    public String putEditUser(Model model,
                              @ModelAttribute("user") UserDTO userDTO,
                              @PathVariable int id) {

        // Comment récupère le http status ?
        restTemplate.put(url.append(id).toString(), userDTO);

       return "redirect:/";
    }

    /**
     * Delete a specified user
     * @param model the Model
     * @param id user's id
     * @return redirect to a specified page
     */
    @RequestMapping("/delete/{id}")
    public String deleteUser(Model model,
                             @PathVariable int id) {

        restTemplate.delete(url.append(id).toString());

        return "redirect:/";
    }
}
