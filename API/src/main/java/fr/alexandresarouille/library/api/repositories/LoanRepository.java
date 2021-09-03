package fr.alexandresarouille.library.api.repositories;

import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    /**
     * Retrieve a array of loan belonging by a specified user
     *
     * @param user The specified user
     * @return The array of loan
     */
    Collection<Loan> findAllByUser(User user);

    /**
     * Retrieve a unique loan specified by it user and it book
     *
     * @param user {@link User}
     * @param book {@link Book}
     * @return a optional containing, if exist, the loan
     */
    Optional<Loan> findByUserAndBook(User user, Book book);

    /**
     * Find all exceeded loans not returned in the database
     *
     * @param localDateTime the local date time
     * @return a collection of loans
     */
    @Query("SELECT loan FROM Loan loan WHERE loan.dateEnd <= :localdate AND loan.dateReturned IS NULL")
    Collection<Loan> findAllExceeded(@Param("localdate") LocalDateTime localDateTime);

}
