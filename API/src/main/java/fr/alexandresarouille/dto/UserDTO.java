package fr.alexandresarouille.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * The UserDTO object is used to transfert user's information to the rest controllers.
 * It's not the final user instance with all data stored in the database.
 */
@Data
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
    @Min(4)
    @Max(16)
    private String password;

}
