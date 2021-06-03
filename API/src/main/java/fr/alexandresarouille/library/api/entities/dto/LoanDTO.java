package fr.alexandresarouille.library.api.entities.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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