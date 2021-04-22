package fr.alexandresarouille.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity representing a user stored in the database.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    /**
     * Unique id of the user
     */
    @Id
    private int uniqueId;

    /**
     * Role of the user
     */
    private Role role;

    /**
     * email of the user
     */
    private String email;
    /**
     * name of the user
     */
    private String name;
    /**
     * firstname of the user
     */
    private String firstName;
    /**
     * password of the user
     */
    private String password;
}
