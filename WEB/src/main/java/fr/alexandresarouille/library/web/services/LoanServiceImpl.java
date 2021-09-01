package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Loan;
import fr.alexandresarouille.library.api.entities.User;
import fr.alexandresarouille.library.api.entities.dto.LoanDTO;
import fr.alexandresarouille.library.api.exceptions.EntityNotExistException;
import fr.alexandresarouille.library.web.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public Collection<Loan> findAllByUser(User user, HttpSession session) throws EntityNotExistException {

        if(Objects.isNull(session.getAttribute("username"))
                || Objects.isNull(session.getAttribute("password")) ){
            throw new EntityNotExistException("Vous n'étes pas connecter");
        }

        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();

        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());

        ParameterizedTypeReference<Collection<Loan>> responseType = new ParameterizedTypeReference<Collection<Loan>>() { };
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));

        ResponseEntity<Collection<Loan>> responseEntity =
                restTemplate.exchange(url.append(String.format("/api/users/loans/%s", user.getUniqueId())).toString(), HttpMethod.GET, null, responseType);

        if(responseEntity.getStatusCode().isError())
            throw new EntityNotExistException(responseEntity.getStatusCode().getReasonPhrase());

        return responseEntity.getBody();
    }

    @Override
    public Loan createLoan(LoanDTO loanDTO, HttpSession session) throws EntityNotExistException, UnknownHttpStatusCodeException {

        if(Objects.isNull(session.getAttribute("username"))
                || Objects.isNull(session.getAttribute("password")) ){
            throw new EntityNotExistException("Vous n'étes pas connecter");
        }

        String username = session.getAttribute("username").toString();
        String password = session.getAttribute("password").toString();

        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());

        ResponseEntity<Loan> response;

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(username, password));
        response = restTemplate.postForEntity(url.append("/api/users/loans/").toString(), loanDTO, Loan.class);


        return response.getBody();
    }

    @Override
    public void extendLoan(Integer loanId) {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        restTemplate.put(url.append("/api/users/loans/extend/").append(loanId).toString(), loanId);
    }
}
