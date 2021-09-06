package fr.alexandresarouille.library.batch.services;

import fr.alexandresarouille.library.api.entities.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
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
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("test1@gmail.com", "test1"));
        return restTemplate.exchange(host.append("/api/batch/loans/exceeded").toString(), HttpMethod.GET, null, parameterizedTypeReference).getBody();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
