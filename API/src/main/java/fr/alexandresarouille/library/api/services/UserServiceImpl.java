package fr.alexandresarouille.library.api.services;

import fr.alexandresarouille.library.api.entities.Role;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * {@link UserService}
 */
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
     * {@link UserService#findByMailIfExist(String)}
     */
    @Override
    public User findByMailIfExist(String email) throws UsernameNotFoundException {
              return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * {@link UserService#create(UserDTO)}
     */
    @Override
    public User create(@NotNull UserDTO userDTO) throws EntityExistException {
        Optional<User> optional = findByEmail(userDTO.getEmail());

        if (optional.isPresent())
            throw new EntityExistException("L'utilisateur avec la m??me adresse mail existe d??j??");

        User user = convertToUser(userDTO);

        user.setRole(Role.USER);

        return repository.saveAndFlush(user);
    }

    /**
     * Convert a UserDTO object to a User object
     *
     * @param userDTO {@link UserDTO}
     * @return {@link User}
     */
    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        return user;
    }
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByMailIfExist(s);

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorityList
        );
    }
}
