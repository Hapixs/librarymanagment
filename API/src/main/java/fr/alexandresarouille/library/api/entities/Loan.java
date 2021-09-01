package fr.alexandresarouille.library.api.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Loan {

    public Loan(User user, Book book, LocalDateTime dateStart, LocalDateTime dateEnd, LocalDateTime dateReturned) {
        this.user = user;
        this.book = book;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateReturned = dateReturned;
    }

    public Loan() {}

    @Id @GeneratedValue
    private int uniqueId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;

    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocalDateTime dateReturned;

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

    public LocalDateTime getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDateTime dateReturned) {
        this.dateReturned = dateReturned;
    }

    public boolean isExtendable() {
       return !getDateEnd().isAfter(getDateStart().plusWeeks(4));
    }
    public boolean isExceeded() {
        return !(isExtendable()) && LocalDateTime.now().isAfter(getDateEnd());
    }
}
