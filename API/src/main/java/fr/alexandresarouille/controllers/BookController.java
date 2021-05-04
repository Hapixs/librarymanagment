package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.BookDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/books")
public class BookController {

    /**
     * Instance of the book service called by the rest methods
     *
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
    @PostMapping("/create")
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO) throws EntityExistException {
        return new ResponseEntity<>(bookService.create(bookDTO), HttpStatus.CREATED);
    }

    /**
     * find a specified book by is id
     *
     * @param id the book's id
     * @return {@link Book}
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Book> findById(@PathVariable int id) throws EntityNotExistException {
        return new ResponseEntity<>(bookService.findByIdIfExist(id), HttpStatus.OK);
    }

    /**
     * List all registered books in the database
     *
     * @param pageable the pageable param to return
     * @return a page of book
     */
    @GetMapping("/listAll")
    public ResponseEntity<Page<Book>> listAllBook(@NotNull Pageable pageable) {
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.OK);
    }
}
