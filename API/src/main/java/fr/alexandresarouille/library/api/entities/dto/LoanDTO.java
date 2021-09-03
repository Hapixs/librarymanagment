package fr.alexandresarouille.library.api.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * The LoanDTO object is used to transfert loan's information to the rest controllers.
 * It's not the final loan instance with all data stored in the database.
 */
public class LoanDTO {

    public LoanDTO(int userId, int bookId) {
        this.userId=userId;
        this.bookId=bookId;
    }

    /**
     * The user's id
     */
    @NotNull
    @Positive
    private int userId;

    /**
     * The book's id
     */
    @NotNull
    @Positive
    private int bookId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}