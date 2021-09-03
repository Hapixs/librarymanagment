package fr.alexandresarouille.librairy.web.services;

import fr.alexandresarouille.entities.Loan;
import fr.alexandresarouille.entities.User;
import fr.alexandresarouille.exceptions.EntityNotExistException;
import fr.alexandresarouille.librairy.web.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class LoanServiceImpl implements LoanService{

    private static final StringBuilder url = Application.restHostURL.append("loans/");

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Collection<Loan> findAllByUser(User user) throws EntityNotExistException {
        ParameterizedTypeReference<Collection<Loan>> responseType = new ParameterizedTypeReference<Collection<Loan>>() { };
        ResponseEntity<Collection<Loan>> response = restTemplate.exchange(url.append("/fromUser/").toString(), HttpMethod.GET, null, responseType, user);

        if(response.getStatusCode().isError())
            throw new EntityNotExistException(response.getStatusCode().getReasonPhrase());

        return response.getBody();
    }
}
