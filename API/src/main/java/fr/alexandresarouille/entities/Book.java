package fr.alexandresarouille.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Entity representing a book stored in the database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    /**
     * Unique id of the book
     */
    @Id
    private int uniqueId;

    /**
     * Name of the book
     */
    private String name;

    /**
     * author of the book
     */
    private String author;

    /**
     * quantity in stock of the book
     */
    private Integer quantity;
}
