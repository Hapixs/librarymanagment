package fr.alexandresarouille.librairy.web.services;

import fr.alexandresarouille.entities.Book;
import fr.alexandresarouille.librairy.web.Application;
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

    private static final StringBuilder url = Application.restHostURL.append("books/");

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Page<Book> findAllBook(Pageable pageable) {
        ParameterizedTypeReference<Page<Book>> responseType = new ParameterizedTypeReference<Page<Book>>() { };
        return restTemplate.exchange(url.toString(), HttpMethod.GET, null, responseType, pageable).getBody();
    }

    @Override
    public Book findById(int id) {
        return restTemplate.getForEntity(url.append(id).toString(), Book.class).getBody();
    }
}
