package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.BookDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    private Book createBook(@NotNull BookDTO bookDTO) throws EntityExistException {
        return bookService.create(convertToBook(bookDTO));
    }

    @PutMapping("{id}")
    private Book editBook(@NotNull @PathVariable int id, @NotNull BookDTO bookDTO) throws EntityNotExistException {
        return bookService.edit(id, convertToBook(bookDTO));
    }

    @DeleteMapping("{id}")
    private void deleteBook(@NotNull @PathVariable int id) throws EntityNotExistException {
        bookService.delete(id);
    }

    private Book convertToBook(@NotNull BookDTO bookDTO) {
        Book book = new Book();
        book.setQuantity(bookDTO.getQuantity());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        return book;
    }
}
