package fr.alexandresarouille.library.api.repositories;

import fr.alexandresarouille.library.api.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * Custom query used to search for specified book
     *
     * @param pageable The page
     * @param name the name of wanted book
     * @param author the author of wanted book
     * @param available specified to return only available books
     * @return a page of book containing those filters
     */
    @Query("SELECT bk FROM Book bk WHERE " +
            "(:author IS NULL OR bk.author=:author)" +
            "AND (:name IS NULL OR bk.name=:name)" +
            "AND (:available IS NULL OR bk.quantity>1)")
    Page<Book> findAllByFilters(Pageable pageable,
                                @Param("name") String name,
                                @Param("author") String author,
                                @Param("available") Boolean available);
}
