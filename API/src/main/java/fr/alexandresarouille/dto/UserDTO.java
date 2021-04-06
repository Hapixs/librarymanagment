package fr.alexandresarouille.dto;

import fr.alexandresarouille.entities.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

    @NotNull
    private String firstName;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Min(4)
    private String password;

    @NotNull
    private Role role;
}
