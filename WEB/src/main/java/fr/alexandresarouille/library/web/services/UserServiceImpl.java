package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.UserDTO;
import fr.alexandresarouille.library.api.exceptions.EntityExistException;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.web.ApplicationProperties;
import fr.alexandresarouille.library.web.entities.UserCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void createUser(UserDTO userDTO) throws EntityExistException {
        try{
            StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
            ResponseEntity<Object> response = restTemplate.postForEntity(url.append("/api/all/users").toString(), userDTO, Object.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HttpSession updateHttpSession(HttpSession httpSession, UserCredential userCredential) throws Throwable {

        User user = findByUsername(userCredential.getUsername(), userCredential.getPassword());

        httpSession.setAttribute("username", userCredential.getUsername());
        httpSession.setAttribute("password", userCredential.getPassword());
        httpSession.setAttribute("logged", true);
        httpSession.setAttribute("role", user.getRole());
        httpSession.setAttribute("userid", user.getUniqueId());
        return httpSession;
    }

    @Override
    public User findByUsername(String username, String password) {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        ResponseEntity<User> response = restTemplate.getForEntity(url.append(String.format("/api/users/users/%s", username)).toString(), User.class);

        if(response.getStatusCode().isError())
            throw new UsernameNotFoundException("Informations de connexion incorectes.");


        return response.getBody();
    }

}
