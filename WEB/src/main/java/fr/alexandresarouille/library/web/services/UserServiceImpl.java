package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.web.ApplicationProperties;
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

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User createUser(UserDTO userDTO) throws EntityExistException  {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        ResponseEntity<Object> response = restTemplate.postForEntity(url.append("/all/users").toString(), userDTO, Object.class);

        if (response.getStatusCode().isError() && response.getBody() instanceof Exception)
            throw new EntityExistException(((Exception) response.getBody()).getMessage());

        return ((User) response.getBody());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        return restTemplate.getForEntity(url.append(String.format("/api/all/users/%s", s)).toString(), UserDetails.class).getBody();
    }
}
