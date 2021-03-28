package fr.alexandresarouille.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private int uniqueId;

}
