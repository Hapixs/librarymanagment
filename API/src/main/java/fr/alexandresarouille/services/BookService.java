package fr.alexandresarouille.services;


import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;

import java.util.Optional;

public interface BookService {

    /**
     * Get or not a book from the data base by his id
     * @param id -> Id of the book
     * @return an optional containing the book if he was found
     */
    Optional<Book> findById(int id);

    /**
     * Get a book from the data base by his id
     * @param id -> Id of the book
     * @return -> The book by his id
     * @throws EntityNotExistException -> Throwed if the id dosn't match with any book
     */
    Book findByIdIfExist(int id) throws EntityNotExistException;

    /**
     * Get or not a book from the data base by his name
     * @param name -> name of the book
     * @return an optional containing the book if he was found
     */
    Optional<Book> findByName(String name);

    /**
     * Create an new unique book object in the data base
     * @param book -> The book to create (without id)
     * @throws EntityExistException -> Throwed if a book with the same name already exist
     */
    void create(Book book) throws EntityExistException;

    /**
     * Delete a unique book object in the data base by is id
     * @param id -> The id of the targeted book to be deleted
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any book
     */
    void delete(int id) throws EntityNotExistException;

    /**
     * Edit à book data by is id
     * @param id -> Id of the targeted book
     * @param book -> changes to make
     * @throws EntityNotExistException -> throwed if the id dosn't watch with any book
     */
    void edit(int id, Book book) throws EntityNotExistException;
}
