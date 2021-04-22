package fr.alexandresarouille.dao;

import fr.alexandresarouille.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * @param name Name of the book
     * @return An optional containing a book if the name match
     */
    Optional<Book> findByName(String name);
}
