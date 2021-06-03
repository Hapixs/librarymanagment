package fr.alexandresarouille.library.web.services;

import fr.alexandresarouille.library.api.entities.Book;
import fr.alexandresarouille.library.web.ApplicationProperties;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        ParameterizedTypeReference<Page<Book>> responseType = new ParameterizedTypeReference<Page<Book>>() { };
        return restTemplate.exchange(url.append("/all/books").toString(), HttpMethod.GET, null, responseType, pageable).getBody();
    }

    @Override
    public Book findById(int id) {
        StringBuilder url = new StringBuilder(applicationProperties.getRestHostAddress());
        return restTemplate.getForEntity(url.append(String.format("/all/books/%s", id)).toString(), Book.class).getBody();
    }
}
