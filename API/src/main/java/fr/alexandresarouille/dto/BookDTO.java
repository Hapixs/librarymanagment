package fr.alexandresarouille.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
public class BookDTO {

    @NotNull(message = "Le livre doit contenir un nom.")
    private String name;

    @NotNull(message = "Le livre doit contenir un auteur")
    private String author;

    @NotNull(message = "Le livre doit avoir une quantité d'au moins 0")
    @PositiveOrZero
    private int quantity;
}
