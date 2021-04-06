package fr.alexandresarouille.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class LoanDTO {

    @NotNull
    @Positive
    private int userId;

    @NotNull
    @Positive
    private int bookId;

    @NotNull
    private Date dateStart;

    @NotNull
    private Date dateEnd;
}
