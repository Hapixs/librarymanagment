package fr.alexandresarouille.dao;

import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    /**
     * Retrieve a array of loan belonging by a specified user
     *
     * @param pageRequest The array of loans
     * @param user The specified user
     * @return The array of loan
     */
    Page<Loan> findAllByUser(PageRequest pageRequest, User user);

    /**
     * Retrieve a unique loan specified by it user and it book
     *
     * @param user {@link User}
     * @param book {@link Book}
     * @return a optional containing, if exist, the loan
     */
    Optional<Loan> findByUserAndBook(User user, Book book);
}
