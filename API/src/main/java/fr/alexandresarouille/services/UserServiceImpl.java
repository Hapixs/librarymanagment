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
            throw new EntityNotExistException(User.class, id);

        return optionalUser.get();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void create(User user) throws EntityExistException {
        Optional<User> optionalUser = findByEmail(user.getEmail());
        if(optionalUser.isPresent())
            throw new EntityExistException(User.class, optionalUser.get().getUniqueId());

        if(user.getRole() == null) user.setRole(Role.USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        repository.saveAndFlush(user);
    }

    @Override
    public void delete(int id) throws EntityNotExistException {
        repository.delete(findByIdIfExist(id));
    }

    @Override
    public void edit(int id, User user) throws EntityNotExistException {
        User target = findByIdIfExist(id);

        target.setEmail((user.getEmail() == null || user.getEmail().isEmpty()) ? target.getEmail() : user.getEmail());
        target.setFirstName((user.getFirstName() == null || user.getFirstName().isEmpty()) ? target.getFirstName() : user.getFirstName());
        target.setName((user.getName() == null || user.getName().isEmpty()) ? target.getName() : user.getName());
        target.setPassword((user.getPassword() == null || user.getPassword().isEmpty()) ? target.getPassword() : user.getPassword());
        target.setRole((user.getRole() == null) ? target.getRole() : user.getRole());

        repository.saveAndFlush(target);
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
