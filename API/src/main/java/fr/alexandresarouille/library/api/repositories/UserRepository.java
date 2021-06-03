package fr.alexandresarouille.library.api.repositories;

import fr.alexandresarouille.library.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Search a user in the database refered to the email
     *
     * @param email The email of the wanted user
     * @return An optional object containing the user if he was found
     */
    Optional<User> findByEmail(String email);
}
