package fr.alexandresarouille.library.web.controllers;


import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.LoanDTO;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.web.services.LoanService;
import fr.alexandresarouille.library.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Objects;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private UserService userService;

    @GetMapping("loan")
    public String createLoan(Model model,
                             @RequestParam("bookid") int bookId,
                             @RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "warn", required = false) String warn,
                             @RequestParam(value = "success", required = false) String success,
                             HttpSession httpSession,
                             RedirectAttributes redirectAttributes) throws EntityNotExistException {

        model.addAttribute("error", error);
        model.addAttribute("success", success);
        model.addAttribute("warn", warn);

        if(Objects.isNull(httpSession.getAttribute("logged"))) {
            redirectAttributes.addAttribute("error", "Vous devez être connecter pour faire ceci.");
            return "redirect:/";
        }

        try {
            loanService.createLoan(new LoanDTO((Integer) httpSession.getAttribute("userid"), bookId), httpSession);
            redirectAttributes.addAttribute("success", "Prêts effectuer avec succes !");
        } catch (UnknownHttpStatusCodeException e) {
            redirectAttributes.addAttribute("error", e.getMessage());
        }
        return "redirect:/loans";
    }

    @GetMapping
    public String getUserLoans(Model model,
                               @RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "warn", required = false) String warn,
                               @RequestParam(value = "success", required = false) String success,
                               HttpSession httpSession,RedirectAttributes redirectAttributes) throws EntityNotExistException {

        if(Objects.isNull(httpSession.getAttribute("logged"))) {
            redirectAttributes.addAttribute("error", "Vous devez être connecter pour faire ceci.");
            return "redirect:/";
        }

        User user = userService.findByUsername(httpSession.getAttribute("username").toString(), httpSession.getAttribute("password").toString());

        Collection<Loan> loans = loanService.findAllByUser(user, httpSession);

        model.addAttribute("loans", loans);
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        model.addAttribute("warn", warn);
        return "users/loans";
    }

    @GetMapping("/extend")
    public String extendLoanForUser(@RequestParam("loanid") Integer loanId,
                                    @RequestParam(value = "error", required = false) String error,
                                    @RequestParam(value = "warn", required = false) String warn,
                                    @RequestParam(value = "success", required = false) String success,
                                    Model model, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        if(Objects.isNull(httpSession.getAttribute("logged"))) {
            redirectAttributes.addAttribute("error", "Vous devez être connecter pour faire ceci.");
            return "redirect:/";
        }

        User user = userService.findByUsername(httpSession.getAttribute("username").toString(), httpSession.getAttribute("password").toString());
        loanService.extendLoan(loanId);
        model.addAttribute("error", error);
        model.addAttribute("success", success);
        model.addAttribute("warn", warn);
        return "redirect:/loans";
    }

}
