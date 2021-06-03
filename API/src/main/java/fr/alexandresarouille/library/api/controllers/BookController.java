package fr.alexandresarouille.library.api.controllers;

import fr.alexandresarouille.library.api.entities.dto.BookDTO;
import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.api.services.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/api")
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
    @ApiOperation(value = "Create a new book in the database", response = Book.class, tags = "createBook")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Book created"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = EntityExistException.errorCode, message = "Entity already exist")
    })
    @PostMapping("/admin/books")
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
    @ApiOperation(value = "Get a book from his id", response = Book.class, tags = "findBookById")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "Found"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = EntityNotExistException.errorCode, message = "Not found")
    })
    @GetMapping("/all/books/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) throws EntityNotExistException {
        return new ResponseEntity<>(bookService.findByIdIfExist(id), HttpStatus.FOUND);
    }

    /**
     * List all registered books in the database
     *
     * @param pageable the pageable param to return
     * @return a page of book
     */
    @ApiOperation(value = "Get the list of all books", response = Page.class, tags = "listAllBook")
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "FOUND"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    @GetMapping("/all/books")
    public ResponseEntity<Page<Book>> listAllBook(@NotNull Pageable pageable) {
        return new ResponseEntity<>(bookService.findAll(pageable), HttpStatus.FOUND);
    }


}
