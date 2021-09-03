package fr.alexandresarouille.library.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class IndexController {

    public String getIndex(HttpSession httpSession) {
        return "index";
    }
}
