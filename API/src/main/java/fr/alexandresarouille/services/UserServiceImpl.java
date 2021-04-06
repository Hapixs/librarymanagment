package fr.alexandresarouille.services;

import fr.alexandresarouille.dao.UserRepository;
import fr.alexandresarouille.entities.Role;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public User findByIdIfExist(int id) throws EntityNotExistException {
        Optional<User> optionalUser = findById(id);
        if(!optionalUser.isPresent())
            throw new EntityNotExistException("Cette utilisateur n'existe pas ou plus.");

        return optionalUser.get();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User create(User user) throws EntityExistException {
        Optional<User> optionalUser = findByEmail(user.getEmail());
        if(optionalUser.isPresent())
            throw new EntityExistException("L'utilisateur avec la même adresse mail existe déjà");

        if(user.getRole() == null) user.setRole(Role.USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        return repository.saveAndFlush(user);
    }

    @Override
    public void delete(int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public User edit(int id, User user) throws EntityNotExistException {
        User target = findByIdIfExist(id);
        target.setEmail(user.getEmail());
        target.setFirstName(user.getFirstName());
        target.setName(user.getName());
        target.setPassword(user.getPassword());
        target.setRole(user.getRole());
        return repository.saveAndFlush(target);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = findByEmail(email);

        if(!optionalUser.isPresent())
            throw new UsernameNotFoundException("Utilisateur introuvable");

        User user = optionalUser.get();
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole().toString()));



        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), authorityList
        );
    }
}
