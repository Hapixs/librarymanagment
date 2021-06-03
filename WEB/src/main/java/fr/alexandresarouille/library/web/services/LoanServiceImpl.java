package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.web.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public Collection<Loan> findAllByUser(User user, Authentication authentication) throws EntityNotExistException {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        if(authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor( ((UserDetails) authentication.getPrincipal()).getUsername(), ((UserDetails) authentication.getPrincipal()).getPassword()));

        ParameterizedTypeReference<Collection<Loan>> responseType = new ParameterizedTypeReference<Collection<Loan>>() { };
        ResponseEntity<Collection<Loan>> response = restTemplate.exchange(url.append(String.format("/users/loans/%s", user.getUniqueId())).toString(), HttpMethod.GET, null, responseType);

        if(response.getStatusCode().isError())
            throw new EntityNotExistException(response.getStatusCode().getReasonPhrase());

        return response.getBody();
    }
}
