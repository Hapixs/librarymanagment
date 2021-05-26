package fr.alexandresarouille.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Entity representing a loan stored in the database.
 */
@Entity
public class Loan {

    public Loan() {
    }

    public Loan(User user, Book book, LocalDateTime dateStart, LocalDateTime dateEnd, LocalDateTime dateReturn) {
        this.user = user;
        this.book = book;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateReturn = dateReturn;
    }

    /**
     * Unique id of the loan
     */
    @Id @GeneratedValue
    private int uniqueId;

    /**
     * User who do the loan
     */
    @ManyToOne
    private User user;

    /**
     * Book that get lend to the user
     */
    @ManyToOne
    private Book book;

    /**
     * Date which the book was loaned
     */
    private LocalDateTime dateStart;
    /**
     * Date which the book need to be return
     */
    private LocalDateTime dateEnd;
    /**
     * Date which the book get return
     */
    private LocalDateTime dateReturn;

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDateTime getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDateTime dateReturn) {
        this.dateReturn = dateReturn;
    }
}
