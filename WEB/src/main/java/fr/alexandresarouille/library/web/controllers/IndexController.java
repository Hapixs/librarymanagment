package fr.alexandresarouille.library.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String getIndex(Model model,
                           @RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "warn", required = false) String warn,
                           @RequestParam(value = "success", required = false) String success) {

        model.addAttribute("error", error);
        model.addAttribute("success", success);
        model.addAttribute("warn", warn);

        return "index";
    }
}
