package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.UserRepository;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> findById(@NotNull int id) {
        return repository.findById(id);
    }

    @Override
    public User findByIdIfExist(@NotNull int id) throws EntityNotExistException {
        return findById(id).orElseThrow(() -> new EntityNotExistException("Cette utilisateur n'existe pas ou plus."));
    }

    @Override
    public Optional<User> findByEmail(@NotNull String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User create(@NotNull User user) throws EntityExistException {
        Optional<User> optional = findByEmail(user.getEmail());
        if(optional.isPresent())
            throw new EntityExistException("L'utilisateur avec la même adresse mail existe déjà");

        return repository.saveAndFlush(user);
    }

    @Override
    public void delete(@NotNull int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public User edit(@NotNull int id, @NotNull User user) throws EntityNotExistException {
        User target = findByIdIfExist(id);
        target.setEmail(user.getEmail());
        target.setFirstName(user.getFirstName());
        target.setName(user.getName());
        target.setPassword(user.getPassword());
        target.setRole(user.getRole());
        return repository.saveAndFlush(target);
    }


    @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        User user = findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorityList
        );
    }
}
