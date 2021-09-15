package fr.alexandresarouille.library.web.controllers;

import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.web.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping
    public String getBookList(Model model, @ModelAttribute(value = "bookFilter") Book bookFilter,
                              @RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "warn", required = false) String warn,
                              @RequestParam(value = "success", required = false) String success,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "items", required = false) Integer items ) {

        model.addAttribute("error", error);
        model.addAttribute("warn", warn);
        model.addAttribute("success", success);

        PageRequest pageRequest = PageRequest.of(
                Optional.ofNullable(page).orElse(0),
                Optional.ofNullable(items).orElse(10)
        );

        model.addAttribute("page", pageRequest.getPageNumber());
        model.addAttribute("items", pageRequest.getPageSize());

        Book booksFilter = Objects.isNull(bookFilter) ? new Book() : bookFilter;

        model.addAttribute("bookFilter", booksFilter);
        model.addAttribute("books", bookService.findAllBook(pageRequest, booksFilter));

        return "/Books/books";
    }
}
