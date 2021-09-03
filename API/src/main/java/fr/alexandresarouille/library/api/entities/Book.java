package fr.alexandresarouille.library.api.entities;


import org.springframework.lang.Nullable;

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

    /**
     * Unique id of the book
     */
    @Id @GeneratedValue
    private int uniqueId;

    /**
     * Name of the book
     */
    @Nullable
    private String name;

    /**
     * author of the book
     */
    @Nullable
    private String author;

    /**
     * quantity in stock of the book
     */
    @Nullable
    private Integer quantity;

    public Book() {
    }

    public int getUniqueId() {
        return this.uniqueId;
    }

    public String getName() {
        return this.name;
    }

    public String getAuthor() {
        return this.author;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        final Book other = (Book) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getUniqueId() != other.getUniqueId()) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        final Object this$quantity = this.getQuantity();
        final Object other$quantity = other.getQuantity();
        if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getUniqueId();
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $quantity = this.getQuantity();
        result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
        return result;
    }

    public String toString() {
        return "Book(uniqueId=" + this.getUniqueId() + ", name=" + this.getName() + ", author=" + this.getAuthor() + ", quantity=" + this.getQuantity() + ")";
    }
}
