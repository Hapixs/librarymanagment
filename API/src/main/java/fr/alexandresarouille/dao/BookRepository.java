package fr.alexandresarouille.dao;

import fr.alexandresarouille.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * find a book by his name
     *
     * @param name Name of the book
     * @return An optional containing a book if the name match
     */
    Optional<Book> findByName(String name);

    /**
     * Find all books in the database
     *
     * @param pageable the page param
     * @return a page of books
     */
    Page<Book> findAll(Pageable pageable);
}
