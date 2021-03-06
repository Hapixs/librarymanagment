package fr.alexandresarouille.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

/**
 * Entity representing a loan stored in the database.
 */
@Data
@NoArgsConstructor
@Entity
public class Loan {

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
}
