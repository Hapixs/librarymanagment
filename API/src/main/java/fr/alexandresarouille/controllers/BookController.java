package fr.alexandresarouille.controllers;

import fr.alexandresarouille.dto.BookDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController("books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    private Book createBook(@NotNull BookDTO bookDTO) throws EntityExistException {
        return bookService.create(convertToBook(bookDTO));
    }

    @PostMapping("/edit/{id}")
    private Book editBook(@NotNull @PathVariable int id, @NotNull BookDTO bookDTO) throws EntityNotExistException {
        return bookService.edit(id, convertToBook(bookDTO));
    }

    @PostMapping("/delete/{id}")
    private void deleteBook(@NotNull @PathVariable int id) throws EntityNotExistException {
        bookService.delete(id);
    }

    private Book convertToBook(BookDTO bookDTO) {
        Book book = new Book();

        book.setQuantity(bookDTO.getQuantity());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());

        return book;
    }
}
