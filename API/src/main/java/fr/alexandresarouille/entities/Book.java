package fr.alexandresarouille.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    private int uniqueId;


    private String name;
    private String author;
    private int quantity;
}
