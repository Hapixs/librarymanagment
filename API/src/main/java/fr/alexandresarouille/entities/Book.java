package fr.alexandresarouille.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Entity representing a book stored in the database.
 */
@Entity
public class Book {

    public Book(String name, String author, Integer quantity) {
        this.name = name;
        this.author = author;
        this.quantity = quantity;
    }

    public Book() {
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

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
