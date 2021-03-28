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
public class User {

    @Id
    private int uniqueId;


    private Role role;

    private String email;
    private String name;
    private String firstName;
    private String password;
}
