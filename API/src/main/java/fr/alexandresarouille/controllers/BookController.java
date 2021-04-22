package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.BookDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Rest controller for the book entity
 * Called with the path: {host}/books/
 *
 * Working with bookService ({@link BookService})
 */
@RestController
@RequestMapping("/books/")
public class BookController {

    /**
     * Instance of the book service called by the rest methods
     * {@link BookService}
     */
    @Autowired
    private BookService bookService;

    /**
     * Create a book instance in the database if no book with the same name already exist
     *
     * see also:
     * {@link BookService#create(BookDTO)}
     *
     * @param bookDTO {@link BookDTO}
     * @return {@link Book}
     * @throws EntityExistException {@link EntityExistException}
     */
    @PostMapping
    public Book createBook(@Valid BookDTO bookDTO) throws EntityExistException {
        return bookService.create(bookDTO);
    }
}
