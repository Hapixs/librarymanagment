package fr.alexandresarouille.library.api.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity representing a user stored in the database.
 */
@Data
@NoArgsConstructor
@Entity
public class User {

    public User(Role role, String email, String name, String firstName, String password) {
        this.role = role;
        this.email = email;
        this.name = name;
        this.firstName = firstName;
        this.password = password;
    }

    /**
     * Unique id of the user
     */
    @Id @GeneratedValue
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
    @JsonIgnore
    private String password;
}
