package fr.alexandresarouille.entities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Entity representing a book stored in the database.
 */
@Data
@NoArgsConstructor
@Entity
public class Book {

    public Book(String name, String author, Integer quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    /**
     * Unique id of the book
     */
    @Id @GeneratedValue
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
