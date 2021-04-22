package fr.alexandresarouille.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

/**
 * The LoanDTO object is used to transfert loan's information to the rest controllers.
 * It's not the final loan instance with all data stored in the database.
 */
@Data
public class LoanDTO {

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
}