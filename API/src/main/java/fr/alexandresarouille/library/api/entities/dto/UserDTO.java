package fr.alexandresarouille.library.api.entities.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


/**
 * The UserDTO object is used to transfert user's information to the rest controllers.
 * It's not the final user instance with all data stored in the database.
 */
public class UserDTO {

    /**
     * The user's first name
     */
    @NotNull
    private String firstName;

    /**
     * The user's name
     */
    @NotNull
    private String name;

    /**
     * The user's email
     */
    @NotNull
    @Email
    private String email;

    /**
     * The user's password
     */
    @NotNull
    private String password;

    public UserDTO() {
    }

    public @NotNull String getFirstName() {
        return this.firstName;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull @Email String getEmail() {
        return this.email;
    }

    public @NotNull String getPassword() {
        return this.password;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setEmail(@NotNull @Email String email) {
        this.email = email;
    }

    public void setPassword(@NotNull String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserDTO)) return false;
        final UserDTO other = (UserDTO) o;
        if (!other.canEqual(this)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        return this$password == null ? other$password == null : this$password.equals(other$password);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "UserDTO(firstName=" + this.getFirstName() + ", name=" + this.getName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }
}
