package fr.alexandresarouille.services;

import fr.alexandresarouille.entities.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Objects;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    @Override
    public Collection<Loan> findAllExceededLoan() {
        StringBuilder host = new StringBuilder(Objects.requireNonNull(environment.getProperty("restHosturl"), "L'url de l'host rest n'est pas valide!"));
        ParameterizedTypeReference<Collection<Loan>> parameterizedTypeReference = new ParameterizedTypeReference<Collection<Loan>>() { };
        return restTemplate.exchange(host.append("/loans").toString(), HttpMethod.GET, null, parameterizedTypeReference).getBody();
    }
}
