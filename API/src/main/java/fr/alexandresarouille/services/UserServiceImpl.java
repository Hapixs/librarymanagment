package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.UserRepository;
import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.Role;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    /**
     * {@link UserService#findById(int)}
     */
    @Override
    public Optional<User> findById(@NotNull int id) {
        return repository.findById(id);
    }

    /**
     * {@link UserService#findByIdIfExist(int)}
     */
    @Override
    public User findByIdIfExist(@NotNull int id) throws EntityNotExistException {
        return findById(id).orElseThrow(() -> new EntityNotExistException("Cette utilisateur n'existe pas ou plus."));
    }

    /**
     * {@link UserService#findByEmail(String)}
     */
    @Override
    public Optional<User> findByEmail(@NotNull String email) {
        return repository.findByEmail(email);
    }

    /**
     * {@link UserService#create(UserDTO)}
     */
    @Override
    public User create(@NotNull UserDTO userDTO) throws EntityExistException {
        Optional<User> optional = findByEmail(userDTO.getEmail());

        if (optional.isPresent())
            throw new EntityExistException("L'utilisateur avec la même adresse mail existe déjà");

        User user = convertToUser(userDTO);

        user.setRole(Role.USER);

        return repository.saveAndFlush(user);
    }

    // TODO: rework the security
   /* @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        User user = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorityList
        );
    }*/

    /**
     * Convert a UserDTO object to a User object
     *
     * @param userDTO {@link UserDTO}
     * @return {@link User}
     */
    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        return user;
    }
}
