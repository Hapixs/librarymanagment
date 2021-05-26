package fr.alexandresarouille.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

/**
 * Entity representing a user stored in the database.
 */
@Entity
public class User {

    public User() {
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
