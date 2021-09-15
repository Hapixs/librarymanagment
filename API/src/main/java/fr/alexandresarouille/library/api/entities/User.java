package fr.alexandresarouille.library.api.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Entity representing a user stored in the database.
 */
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

    public User() {
    }

    public int getUniqueId() {
        return this.uniqueId;
    }

    public Role getRole() {
        return this.role;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonIgnore
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual(this)) return false;
        if (this.getUniqueId() != other.getUniqueId()) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        return this$password == null ? other$password == null : this$password.equals(other$password);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getUniqueId();
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "User(uniqueId=" + this.getUniqueId() + ", role=" + this.getRole() + ", email=" + this.getEmail() + ", name=" + this.getName() + ", firstName=" + this.getFirstName() + ", password=" + this.getPassword() + ")";
    }
}
