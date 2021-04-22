package fr.alexandresarouille.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Entity representing a loan stored in the database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Loan {

    /**
     * Unique id of the loan
     */
    @Id
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
