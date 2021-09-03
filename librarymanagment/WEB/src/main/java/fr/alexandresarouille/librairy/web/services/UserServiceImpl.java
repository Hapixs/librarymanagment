package fr.alexandresarouille.librairy.web.services;

import fr.alexandresarouille.dto.UserDTO;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.errors.ApiError;
import fr.alexandresarouille.exceptions.EntityExistException;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.librairy.web.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final StringBuilder url = Application.restHostURL.append("users/");

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(UserDTO userDTO) throws EntityExistException  {
        ResponseEntity<Object> response = restTemplate.postForEntity(url.toString(), userDTO, Object.class);

        if (response.getStatusCode().isError() && response.getBody() instanceof ApiError)
            throw new EntityExistException(((ApiError) response.getBody()).getMessage());

        return ((User) response.getBody());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return restTemplate.getForEntity(url.append("/loadfromusername").toString(), UserDetails.class, s).getBody();
    }
}
