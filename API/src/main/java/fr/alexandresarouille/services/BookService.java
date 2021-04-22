package fr.alexandresarouille.services;


import fr.alexandresarouille.dto.BookDTO;
import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface BookService {

    /**
     * Get or not a book from the data base by his id
     *
     * @param id Id of the book
     * @return an optional containing the book if he was found
     */
    Optional<Book> findById(@NotNull int id);

    /**
     * Get a book from the data base by his id
     *
     * @param id Id of the book
     * @return The book by his id ({@link Book})
     * @throws EntityNotExistException {@link EntityNotExistException}
     */
    Book findByIdIfExist(@NotNull int id) throws EntityNotExistException;

    /**
     * Get or not a book from the data base by his name
     *
     * @param name name of the book
     * @return an optional containing the book if he was found
     */
    Optional<Book> findByName(@NotNull String name);

    /**
     * Create an new unique book object in the data base
     *
     * @param bookDTO {@link BookDTO}
     * @return The created book ({@link Book})
     * @throws EntityExistException {@link EntityExistException}
     */
    Book create(@NotNull BookDTO bookDTO) throws EntityExistException;
}
