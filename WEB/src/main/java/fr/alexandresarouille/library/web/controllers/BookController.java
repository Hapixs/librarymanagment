package fr.alexandresarouille.library.web.controllers;

import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.web.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/books/")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public String getBookList(Model model, Authentication authentication) {


        Pageable pageable = PageRequest.of(0, 10);
        model.addAttribute("books", bookService.findAllBook(pageable));

        return "";
    }

    @GetMapping("/{id}")
    public String getBookInformation(Model model, @PathVariable int id) {

        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "";
    }
}
