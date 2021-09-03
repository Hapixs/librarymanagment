package fr.alexandresarouille.library.api.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * The BookDTO object is used to transfert book's information to the rest controllers.
 * It's not the final book instance with all data stored in the database.
 */
public class BookDTO {

    /**
     * The book's name
     */
    @NotNull(message = "Le livre doit contenir un nom.")
    private String name;

    /**
     * The book's author
     */
    @NotNull(message = "Le livre doit contenir un auteur")
    private String author;

    /**
     * The book's available quantity
     */
    @NotNull(message = "Le livre doit avoir une quantité d'au moins 0")
    @PositiveOrZero
    private int quantity;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
